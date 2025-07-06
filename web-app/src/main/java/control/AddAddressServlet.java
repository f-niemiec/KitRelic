package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AddressBean;
import model.AddressDS;
import model.TipoIndirizzo;

import java.io.IOException;


@WebServlet("/AddAddressServlet")
public class AddAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddAddressServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("logmail") == null) {
			response.sendRedirect(request.getContextPath() + "/resources/common/Login.jsp");
		    return;
		}

		String email = (String) session.getAttribute("logmail");
		int userId = model.UserDS.getIDByMail(email);

		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String province = request.getParameter("province");
		String country = request.getParameter("country");
		String addressTypeParam = request.getParameter("addressType");

		if (city == null || street == null || province == null || country == null 
				|| addressTypeParam == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametri mancanti");
		    return;
		}
		AddressDS ds = new AddressDS();
		AddressBean newAddress = new AddressBean();
		newAddress.setUserId(userId);
		newAddress.setCity(city);
		newAddress.setStreet(street);
		newAddress.setProvince(province);
		newAddress.setCountry(country);
		newAddress.setActive(false);
		try {
			newAddress.setAddress(TipoIndirizzo.valueOf(addressTypeParam));
		} catch (IllegalArgumentException e) {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo indirizzo non valido");
		    return;
		}
		int newId = ds.doSave(newAddress);
		if (newId > 0) {
			ds.doUpdateActive(newId);
			if ("Billing".equalsIgnoreCase(addressTypeParam)) {
			    request.getSession().setAttribute("billingSaved", true);
			} else if ("Shipping".equalsIgnoreCase(addressTypeParam)) {
			    request.getSession().setAttribute("shippingSaved", true);
			}
			String info = request.getParameter("fromInfo");
			if(info!= null && info.equalsIgnoreCase("true")) {
				response.sendRedirect(request.getContextPath() + "/resources/common/info.jsp");
			} else
				response.sendRedirect(request.getContextPath() + "/resources/common/checkout.jsp"); 
		} else {
		    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel salvataggio dell'indirizzo");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}