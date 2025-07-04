package control;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AddressBean;
import model.AddressDS;
import model.Cart;
import model.CartItemBean;
import model.OrderBean;
import model.OrderDS;
import model.OrderItemBean;
import model.PaymentCardBean;
import model.ProductBean;
import model.ProductDS;
import model.TipoIndirizzo;

@WebServlet("/CompleteOrderServlet")
public class CompleteOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userID") == null || session.getAttribute("cart") == null || session.getAttribute("paymentCard") == null) {
            response.sendRedirect(request.getContextPath() + "/resources/common/Login.jsp");
            return;
        }
        int userId = (int) session.getAttribute("userID");
        Cart cart = (Cart) session.getAttribute("cart");
        PaymentCardBean card = (PaymentCardBean) session.getAttribute("paymentCard");
        AddressBean billing = (AddressBean) session.getAttribute("activeBilling");
        AddressBean shipping = (AddressBean) session.getAttribute("activeShipping");
        String sameAsBilling = request.getParameter("sameAsBilling");
        if("true".equals(sameAsBilling) && billing != null) {
            shipping = new AddressBean();
            shipping.setUserId(billing.getUserId());
            shipping.setCity(billing.getCity());
            shipping.setStreet(billing.getStreet());
            shipping.setProvince(billing.getProvince());
            shipping.setCountry(billing.getCountry());
            shipping.setActive(true);
            shipping.setAddress(TipoIndirizzo.Shipping);
            AddressDS addressDS = new AddressDS();
            int newShippingId = addressDS.doSave(shipping);
            addressDS.doUpdateActive(newShippingId);
            shipping.setId(newShippingId);
            session.setAttribute("shippingSaved", true);
        }
        if(billing == null || shipping == null) {
            response.sendRedirect(request.getContextPath() + "/resources/common/checkout.jsp");
            return;
        }
        ProductDS productDS = new ProductDS();
        boolean available = true;
        for (CartItemBean item : cart.getCart()) {
            ProductBean product = productDS.doRetrieveByKey(item.getId());
            if (product.getQuantity() < item.getQuantity()) {
                available = false;
                break;
            }
        }
        if (!available) {
        	response.sendRedirect(request.getContextPath() + "/resources/error-pages/500.jsp");
            return;
        }
        OrderBean ordine = new OrderBean();
        ordine.setUserId(userId);
        ordine.setUserEmail((String) session.getAttribute("logmail"));
        ordine.setUserName((String) session.getAttribute("logname"));
        ordine.setUserSurname((String) session.getAttribute("logsurname"));
        ordine.setCard(card);
        ordine.setBilling(billing);
        ordine.setShipping(shipping);
        ordine.setDate(LocalDate.now());
        ordine.setTotal(cart.getTotalPrice());
        List<OrderItemBean> items = new ArrayList<>();
        for(CartItemBean item : cart.getCart()) {
            OrderItemBean orderItem = new OrderItemBean();
            orderItem.setProductId(item.getId());
            orderItem.setProductName(item.getName());
            orderItem.setProductSize(item.getSize());
            orderItem.setProductPrice(item.getProduct().getPrice());
            orderItem.setQuantity(item.getQuantity());
            items.add(orderItem);
        }
        ordine.setItems(items);
        for (CartItemBean item : cart.getCart()) {
            productDS.doUpdateQuantity(item.getId(), -item.getQuantity());
        }
        OrderDS orderDS = new OrderDS();
        int orderId = orderDS.doSave(ordine);
        if(orderId == 0) {
            response.sendRedirect(request.getContextPath() + "/resources/common/checkout.jsp");
            return;
        }
        session.removeAttribute("cart");
        session.removeAttribute("paymentCard");
        session.removeAttribute("activeBilling");
        session.removeAttribute("activeShipping");
        session.setAttribute("confirmedOrder", ordine);
        response.sendRedirect(request.getContextPath() + "/resources/common/invoice.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}