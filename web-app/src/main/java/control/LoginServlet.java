package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginBean;
import model.LoginDS;
import model.UserDS;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LoginBean bean = new LoginBean();
		bean.setEmail(email);
		bean.setPassword(password);
		
		LoginDS ds = new LoginDS();
		boolean success = ds.userExists(bean);
		if(success) {
			UserDS user = new UserDS();
			HttpSession session = request.getSession();
			session.setAttribute("logmail", email);
			session.setAttribute("logtype", user.getTypeByMail(email));
			session.setAttribute("logname", user.getNameByMail(email));
			if(user.getTypeByMail(email).equals("Admin")) {
				response.sendRedirect(request.getContextPath() + "/resources/admin/catalogue.jsp");
			}
			else
				response.sendRedirect("404.jsp"); //Solo come placeholder ovviamente non è un pattern valido
		}
		else {
			System.out.println("Tentativo di login fallito per :" + email);
			response.sendRedirect(request.getContextPath() + "/resources/common/Login.jsp?error=true");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
