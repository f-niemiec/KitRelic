package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItemBean;
import model.ProductBean;
import model.ProductDS;

import java.io.IOException;


@WebServlet("/CartControlServlet")
public class CartControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartControlServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDS ds = new ProductDS();
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equalsIgnoreCase("add")) {
				int id = Integer.parseInt(request.getParameter("productId"));
				int qnt = Integer.parseInt(request.getParameter("quantity"));
				ProductBean bean = ds.doRetrieveByKey(id);
				for(int i = 0; i < qnt ; i++) {
					cart.addProduct(bean);
				}
				request.setAttribute("productBean", bean);
				if(request.getParameter("fromCart")!=null && request.getParameter("fromCart")
						.equalsIgnoreCase("true")) {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/cart.jsp");
					dispatcher.forward(request, response);
					return;
				} else {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/product.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			if(action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(request.getParameter("itemId"));
				ProductBean bean = ds.doRetrieveByKey(id);
				cart.deleteProduct(bean);
				request.setAttribute("productBean", bean);
				if(request.getParameter("fromCart")!=null && request.getParameter("fromCart")
						.equalsIgnoreCase("true")) {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/cart.jsp");
					dispatcher.forward(request, response);
					return;
				} else {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/product.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			if(action.equalsIgnoreCase("increment")) {
				int id = Integer.parseInt(request.getParameter("itemId"));
				ProductBean bean = ds.doRetrieveByKey(id);
				CartItemBean item = cart.getItem(id);
				item.increment();
				request.setAttribute("productBean", bean);
				if(request.getParameter("fromCart")!=null && request.getParameter("fromCart")
						.equalsIgnoreCase("true")) {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/cart.jsp");
					dispatcher.forward(request, response);
					return;
				} else {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/product.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			if(action.equalsIgnoreCase("decrement")) {
				int id = Integer.parseInt(request.getParameter("itemId"));
				ProductBean bean = ds.doRetrieveByKey(id);
				CartItemBean item = cart.getItem(id);
				item.decrement();
				request.setAttribute("productBean", bean);
				if(request.getParameter("fromCart")!=null && request.getParameter("fromCart")
						.equalsIgnoreCase("true")) {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/cart.jsp");
					dispatcher.forward(request, response);
					return;
				} else {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/product.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			if(action.equalsIgnoreCase("clear")) {
				cart.clear();
				if(request.getParameter("fromCart")!=null && request.getParameter("fromCart")
						.equalsIgnoreCase("true")) {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/cart.jsp");
					dispatcher.forward(request, response);
					return;
				} else {
					RequestDispatcher dispatcher = this.getServletContext().
							getRequestDispatcher("/resources/common/product.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
		}
		
		double total = cart.getTotalPrice();
		session.setAttribute("totalPrice", total);
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
