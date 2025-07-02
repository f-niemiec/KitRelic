package control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.PaymentCardBean;
import model.PaymentCardDS;

@WebServlet("/AddPaymentCardServlet")
public class AddPaymentCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userID") == null) {
            response.sendRedirect(request.getContextPath() + "/resources/common/Login.jsp");
            return;
        }
        PaymentCardDS paymentCardDS = new PaymentCardDS();
        int userId = (int) session.getAttribute("userID");
        String cardNumberStr = request.getParameter("cardNumber");
        String owner = request.getParameter("owner");
        String expiresStr = request.getParameter("expires");
        String cvvStr = request.getParameter("cvv");
        if(cardNumberStr == null || owner == null || expiresStr == null || cardNumberStr.isEmpty() || owner.isEmpty() || expiresStr.isEmpty()) {
            request.setAttribute("error", "Tutti i campi obbligatori devono essere compilati.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }
        LocalDate expiresDate;
        try {
            expiresDate = LocalDate.parse(expiresStr);
        } catch(DateTimeParseException e) {
            request.setAttribute("error", "Formato data non valido. Usa YYYY-MM-DD.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }
        int cardNumber, cvv = 0;
        try {
            cardNumber = Integer.parseInt(cardNumberStr);
            if(cvvStr != null && !cvvStr.isEmpty()) {
                cvv = Integer.parseInt(cvvStr);
            }
        } catch(NumberFormatException e) {
            request.setAttribute("error", "Numero carta e CVV devono essere numeri validi.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }
        PaymentCardBean card = new PaymentCardBean();
        card.setUserId(userId);
        card.setCardNumber(cardNumber);
        card.setOwner(owner);
        card.setExpires(expiresDate);
        card.setCvv(cvv);
        int savedId = paymentCardDS.doSave(card);
        if(savedId == 0) {
            request.setAttribute("error", "Errore durante il salvataggio del metodo di pagamento.");
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
            return;
        }
        card.setId(savedId);
        session.setAttribute("paymentCard", card);
        response.sendRedirect(request.getContextPath() + "/CompleteOrderServlet");
    }
}
