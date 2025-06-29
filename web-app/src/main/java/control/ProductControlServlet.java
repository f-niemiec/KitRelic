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

@WebServlet("/ProductControlServlet")
public class ProductControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ProductControlServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDS ds = new ProductDS();
		String novita = request.getParameter("novita");
		String trend = request.getParameter("trend");
		if (novita != null) {
		    request.setAttribute("novita", novita);
		}
		if (trend != null) {
		    request.setAttribute("trend", trend);
		}

		try {
			Collection<ProductBean> products = ds.doRetrieveAll("ID");
			request.setAttribute("products", products);
			if(novita == null) {
				Collection<ProductBean> newProd = ds.doRetrieveNew();
				request.setAttribute("novita", newProd);
			}
			if(trend == null) {
				Collection<ProductBean> trendProd = ds.doRetrieveTrend();
				request.setAttribute("trend", trendProd);
			}

			RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/resources/common/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
