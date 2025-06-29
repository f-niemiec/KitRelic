package control;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/SearchbarServlet")
public class SearchbarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SearchbarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		if (search == null || search.trim().isEmpty()) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("{\"error\":\"Parametro errato\"}");
	        return;
	    }
		try {
			ProductDS ds = new ProductDS();
	        Collection<ProductBean> results = ds.doRetrieveBySearch(search.trim());
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String json = gson.toJson(results);

	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json);

	    } catch (Exception e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
