package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RegistrationDS;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MatchEmail")
public class MatchEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MatchEmail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		RegistrationDS ds = new RegistrationDS();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print("{\"Exists\":" + ds.emailExists(email) + "}");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
