package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductBean;
import model.ProductDS;

import java.io.IOException;


@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String size = request.getParameter("taglia");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double price = Double.parseDouble(request.getParameter("prezzo"));
		boolean trend = false;
		boolean recent = false;
		if(request.getParameter("trend") != null) {
			trend = true;
		}
		if(request.getParameter("recent") != null) {
			recent = true;
		}
		
		ProductBean bean = new ProductBean();
		bean.setName(name);
		bean.setDescription(description);
		bean.setType(type);
		bean.setSize(size);
		bean.setQuantity(quantity);
		bean.setTrend(trend);
		bean.setRecent(recent);
		bean.setPrice(price);
		
		ProductDS ds = new ProductDS();
		ds.doSave(bean);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/resources/admin/productUpload.jsp?success=true");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
