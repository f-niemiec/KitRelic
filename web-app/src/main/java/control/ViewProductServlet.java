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
import java.sql.SQLException;
import java.util.Collection;


@WebServlet("/ViewProductServlet")
public class ViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDS ds = new ProductDS();
		try {
			Collection<ProductBean> products = ds.doRetrieveAll("ID");
			request.setAttribute("products", products);
			RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/resources/admin/catalogue.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
