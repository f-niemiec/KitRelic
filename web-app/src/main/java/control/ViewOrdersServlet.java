package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderBean;
import model.OrderDS;
import model.ProductBean;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/ViewOrdersServlet")
public class ViewOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewOrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDS ds = new OrderDS();
		try {
			Collection<OrderBean> orders = ds.doRetrieveAll("ID");
			request.setAttribute("orders", orders);
			RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/resources/admin/completed-orders.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
