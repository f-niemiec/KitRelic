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
import java.util.Collection;

@WebServlet("/ProductPage")
public class ProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProductPage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = request.getParameter("prodId");
		if(product == null) {
			response.sendRedirect(request.getContextPath() + "/resources/common/index.jsp");
		}
		int id = Integer.parseInt(product);
		ProductDS ds = new ProductDS();
		try {
			ProductBean bean = ds.doRetrieveByKey(id);
			request.setAttribute("productBean", bean);
			RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/resources/common/product.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
