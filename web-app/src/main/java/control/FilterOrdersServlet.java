package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderBean;
import model.OrderDS;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

@WebServlet("/FilterOrdersServlet")
public class FilterOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public FilterOrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromParam = request.getParameter("from");
        String toParam = request.getParameter("to");
        String userIdParam = request.getParameter("userId");
        OrderDS orderDS = new OrderDS();
        Collection<OrderBean> orders = new LinkedList<>();
        try {
            boolean hasDate = fromParam != null && !fromParam.isEmpty() && toParam != null && !toParam.isEmpty();
            boolean hasUser = userIdParam != null && !userIdParam.isEmpty();
            if (hasDate && hasUser) {
                LocalDate from = LocalDate.parse(fromParam);
                LocalDate to = LocalDate.parse(toParam);
                int userId = Integer.parseInt(userIdParam);
                orders = orderDS.doRetrieveByDateAndUser(from, to, userId);
            } else if (hasDate) {
                LocalDate from = LocalDate.parse(fromParam);
                LocalDate to = LocalDate.parse(toParam);
                orders = orderDS.doRetrieveByDate(from, to);
            } else if (hasUser) {
                int userId = Integer.parseInt(userIdParam);
                orders = orderDS.doRetrieveByUserID(userId);
            } else {
                orders = orderDS.doRetrieveAll("Data");
            }
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/resources/admin/completed-orders.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri: " + e.getMessage());
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
