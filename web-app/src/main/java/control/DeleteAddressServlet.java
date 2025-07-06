package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AddressDS;

import java.io.IOException;

@WebServlet("/DeleteAddressServlet")
public class DeleteAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteAddressServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddressDS ds = new AddressDS();
		String id = request.getParameter("addressId");
		int addId = Integer.parseInt(id);
		ds.doDelete(addId);
		response.sendRedirect(request.getContextPath() + "/resources/common/info.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
