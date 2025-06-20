package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.RegistrationBean;
import model.RegistrationDS;
import model.TipoUtente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("name");
		String cognome = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String hash = Encrypt.toHash(password);
		LocalDate data = null;
		String dataStr = request.getParameter("date");
		if (dataStr != null && !dataStr.isEmpty()) {
		    data = LocalDate.parse(dataStr);
		}
		
		RegistrationBean bean = new RegistrationBean();
		bean.setNome(nome);
		bean.setCognome(cognome);
		bean.setEmail(email);
		bean.setPassword(hash);
		bean.setTipo(TipoUtente.Utente);
		bean.setData(data);
		
		RegistrationDS rds = new RegistrationDS();
		boolean success = rds.insertUser(bean);
		if(success) {
			HttpSession session = request.getSession();
			response.sendRedirect("Login.jsp");
		} else {
			response.sendRedirect("404.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
