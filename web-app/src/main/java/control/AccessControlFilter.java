package control;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AccessControlFilter", urlPatterns = "/resources/admin/*")
public class AccessControlFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		
		String email = null;
		String type = null;
		
		if (session != null) {
            email = (String) session.getAttribute("logmail");
            type = (String) session.getAttribute("logtype");
        }
		if (email == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/resources/common/Login.jsp");
            return;
        }
        if (type == null || !type.equalsIgnoreCase("admin")) {
        	httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403.jsp");
            return;
        }
        chain.doFilter(request, response);
	}
}
