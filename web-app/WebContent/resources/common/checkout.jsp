<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.*"%>
<%
	String email = (String) request.getSession().getAttribute("logmail");
	boolean isLogged = false;
	boolean fromCart = true;
	if(email != null && !email.isEmpty()){
    	isLogged = true;
	} else {
		response.sendRedirect(request.getContextPath() + "/resources/common/Login.jsp?fromCart=" + fromCart);
		return;
	}
	Collection<AddressBean> billing = null;
	Collection<AddressBean> shipping = null;
	Collection<PaymentCardBean> cards = null;
	AddressDS ds = new AddressDS();
	PaymentCardDS pc = new PaymentCardDS();
	int userId = 0;
	if(isLogged){
		userId = UserDS.getIDByMail(email);
		request.getSession().setAttribute("userID", userId);
		billing = ds.doRetrieveBilling(userId);
		shipping = ds.doRetrieveShipping(userId);
		cards = pc.doRetrieveByUserID(userId);
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Checkout</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="${pageContext.request.contextPath}/resources/scripts/checkout.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class = "billing-address">
	<h3 class="billing-title">Indirizzo di fatturazione</h3>
		<%
			if (billing != null & !billing.isEmpty()) {
				AddressBean activeBilling = ds.doRetrieveActiveBilling(userId);
		%>
		<table>
                <thead>
                    <tr>
                        <th>Città</th>
                        <th>Via</th>
                        <th>Provincia</th>
                        <th>Paese</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%= activeBilling.getCity() %></td>
                        <td><%= activeBilling.getStreet() %></td>
                        <td><%= activeBilling.getProvince() %></td>
                        <td><%= activeBilling.getCountry() %></td>
                    </tr>
                </tbody>
            </table>
		<% } else { %>
		<form action="<%=request.getContextPath()%>/AddAddressServlet" method="post">
            <input type="hidden" name="addressType" value="Billing">
            <input type="hidden" name="userID" value="<%=userId%>">
            <label for="city">Città:</label>
            <input type="text" id="city" name="city" required><br/><br/>
            <label for="street">Via:</label>
            <input type="text" id="street" name="street" required><br/><br/>
            <label for="province">Provincia:</label>
            <input type="text" id="province" name="province" required><br/><br/>
            <label for="country">Paese:</label>
            <input type="text" id="country" name="country" required><br/><br/>
            <input type="submit" value="Inserisci" />
        </form>
        <% } %>
	</div>
	
	<label>
   		<input type="checkbox" id="sameAsBilling" name="sameAsBilling" onchange="toggleShippingForm()" />
    	Indirizzo di fatturazione e spedizione coincidono
    </label>
	
	<div class = "shipping-address" id="shippingForm">
		<h3 class="shipping-title">Indirizzo di spedizione</h3>
		<%
			if (shipping != null & !shipping.isEmpty()) {
				AddressBean activeShipping = ds.doRetrieveActiveShipping(userId);
		%>
		<table>
                <thead>
                    <tr>
                        <th>Città</th>
                        <th>Via</th>
                        <th>Provincia</th>
                        <th>Paese</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%= activeShipping.getCity() %></td>
                        <td><%= activeShipping.getStreet() %></td>
                        <td><%= activeShipping.getProvince() %></td>
                        <td><%= activeShipping.getCountry() %></td>
                    </tr>
                </tbody>
            </table>
		<% } else { %>
		<form action="<%=request.getContextPath()%>/AddAddressServlet" method="post">
            <input type="hidden" name="addressType" value="Shipping">
            <input type="hidden" name="userID" value="<%=userId%>">
            <label for="city">Città:</label>
            <input type="text" id="city" name="city" required><br/><br/>
            <label for="street">Via:</label>
            <input type="text" id="street" name="street" required><br/><br/>
            <label for="province">Provincia:</label>
            <input type="text" id="province" name="province" required><br/><br/>
            <label for="country">Paese:</label>
            <input type="text" id="country" name="country" required><br/><br/>
            <input type="submit" value="Inserisci" />
        </form>
        <% } %>
	</div>
	<div class="payment-method">
  		<h3>Inserisci un nuovo metodo di pagamento</h3>
  		<form action="<%=request.getContextPath()%>/AddPaymentCardServlet" method="post">
    		<input type="hidden" name="userID" value="<%=userId%>">
    		<label for="cardNumber">Numero di carta:</label>
    		<input type="text" id="cardNumber" name="cardNumber" maxlength="16"required><br/>
    		<label for="owner">Proprietario:</label>
    		<input type="text" id="owner" name="owner" required><br/>
    		<label for="expires">Data Scadenza (YYYY-MM-DD):</label>
    		<input type="date" id="expires" name="expires" required><br/>
    		<label for="cvv">CVV:</label>
    		<input type="text" id="cvv" name="cvv" maxlength="4"><br/>
    		<input type="submit" value="Aggiungi Metodo di Pagamento">
  		</form>
	</div>
	<div class="order-button">
		<form action="<%=request.getContextPath()%>/CompleteOrderServlet" method="post">
    		<input type="hidden" name="userID" value="<%=userId%>">
    		<input type="submit" value="Procedi all'acquisto" />
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>