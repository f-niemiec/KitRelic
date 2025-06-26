package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductDS;

import java.io.IOException;

@WebServlet("/ModifyProductServlet")
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDS ds = new ProductDS();
		if(action!=null) {
			if(action.equalsIgnoreCase("modify")) {
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				String quantity = request.getParameter("quantity");
				String price = request.getParameter("prezzo");
				if (request.getParameter("trend") != null) {
				    ds.doUpdateTrend(id);
				}
				if (request.getParameter("recent") != null) {
				    ds.doUpdateNew(id);
				}
				if (name != null && !name.trim().isEmpty()) {
				    ds.doUpdateName(id, name);
				} 
				if (description != null && !description.trim().isEmpty()) {
				    ds.doUpdateDescription(id, description);
				} 
				if (quantity != null && !quantity.trim().isEmpty()) {
				    try {
				        int quantita = Integer.parseInt(quantity);
				        ds.doUpdateQuantity(id, quantita);
				    } catch (NumberFormatException e) {
				    	System.out.println("You found the first cat!");
				    }
				}
				if (price != null && !price.trim().isEmpty()) {
				    try {
				        double prezzo = Double.parseDouble(price);
				        ds.doUpdatePrice(id, prezzo);
				    } catch (NumberFormatException e) {
				    	System.out.println("You found the second cat!");
				    }
				}
			} else if(action.equalsIgnoreCase("delete")) {
				ds.doDelete(id);
			}
		}
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/resources/admin/catalogue.jsp?success=true");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response);
	}

}
