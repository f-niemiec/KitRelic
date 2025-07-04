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
        AddressBean activeBilling = ds.doRetrieveActiveBilling(userId);
        AddressBean activeShipping = ds.doRetrieveActiveShipping(userId);
        session.setAttribute("activeBilling", activeBilling);
        session.setAttribute("activeShipping", activeShipping);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="${pageContext.request.contextPath}/resources/scripts/checkout.js" defer></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/checkout.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div class="checkout-wrapper">
<div class="color-insert">
<div class="billing-address">
    <h3 class="billing-title">Indirizzo di fatturazione</h3>
    <%
        if (billing != null && !billing.isEmpty()) {
            AddressBean activeBilling = (AddressBean) session.getAttribute("activeBilling");
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
    <form action="#" method="post">
    	<label>
            <input type="checkbox" id="sameAsBilling" name="sameAsBilling" onchange="toggleShippingForm()">
            Usa lo stesso indirizzo per la spedizione
        </label><br/>
    </form>
    <% } else { %>
    <form action="<%=request.getContextPath()%>/AddAddressServlet" method="post" onsubmit="return validateCheckout(event)">
        <input type="hidden" name="addressType" value="Billing">
        <input type="hidden" name="userID" value="<%=userId%>">
        <label>Città:</label>
        <input type="text" name="city" required>
        <div class="error" id="error-city" style="display: none;">Città non valida</div>
        <label>Via:</label>
        <input type="text" name="street" required>
        <div class="error" id="error-street" style="display: none;">Via non valida</div>
        <label>Provincia:</label>
        <input type="text" name="province" required>
        <div class="error" id="error-province" style="display: none;">Provincia non valida</div>
        <label>Paese:</label>
        <input type="text" name="country" required>
        <div class="error" id="error-country" style="display: none;">Paese non valido</div>
        <input type="submit" value="Inserisci" />
        <label>
            <input type="checkbox" id="sameAsBilling" name="sameAsBilling" onchange="toggleShippingForm()">
            Usa lo stesso indirizzo per la spedizione
        </label><br/>
    </form>
    <% } %>
</div>

<div class="shipping-address" id="shippingForm">
    <h3 class="shipping-title">Indirizzo di spedizione</h3>
    <%
        if (shipping != null && !shipping.isEmpty()) {
            AddressBean activeShipping = (AddressBean) session.getAttribute("activeShipping");
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
    <form action="<%=request.getContextPath()%>/AddAddressServlet" method="post" onsubmit="return validateCheckout(event)">
        <input type="hidden" name="addressType" value="Shipping">
        <input type="hidden" name="userID" value="<%=userId%>">
        <label>Città:</label>
        <input type="text" name="city" required>
        <div class="error" id="error-city" style="display: none;">Città non valida</div>
        <label>Via:</label>
        <input type="text" name="street" required>
        <div class="error" id="error-street" style="display: none;">Via non valida</div>
        <label>Provincia:</label>
        <input type="text" name="province" required>
        <div class="error" id="error-province" style="display: none;">Provincia non valida</div>
        <label>Paese:</label>
        <input type="text" name="country" required>
        <div class="error" id="error-country" style="display: none;">Paese non valido</div>
        <input type="submit" value="Inserisci" />
    </form>
    <% } %>
</div>

<div class="payment-method">
	<%
    	Boolean billingSaved = (Boolean) session.getAttribute("billingSaved");
    	Boolean shippingSaved = (Boolean) session.getAttribute("shippingSaved");
    	if (billingSaved == null) billingSaved = false;
    	if (shippingSaved == null) shippingSaved = false;
	%>
    <form action="<%=request.getContextPath()%>/AddPaymentCardServlet" method="post" onsubmit="return validateCheckout(event)">
        <input type="hidden" name="userID" value="<%=userId%>">
        <input type="hidden" id="billingSaved" value="<%= billingSaved %>"/>
		<input type="hidden" id="shippingSaved" value="<%= shippingSaved %>"/>
		<input type="hidden" name="sameAsBilling" id="hiddenSameAsBilling">
        <h3>Inserisci un nuovo metodo di pagamento</h3>
        <label for="cardNumber">Numero di carta:</label>
        <input type="text" id="cardNumber" name="cardNumber" maxlength="16" required>
        <div class="error" id="error-card" style="display: none;">Numero carta non valido</div>
        <label for="owner">Proprietario:</label>
        <input type="text" id="owner" name="owner" required>
        <div class="error" id="error-owner" style="display: none;">Nome non valido</div>
        <label for="expires">Data Scadenza (YYYY-MM-DD):</label>
        <input type="date" id="expires" name="expires" required>
        <div class="error" id="error-expires" style="display: none;">Data scadenza mancante</div>
        <label for="cvv">CVV:</label>
        <input type="password" id="cvv" name="cvv" maxlength="4" required>
        <div class="error" id="error-cvv" style="display: none;">CVV non valido</div>
        <div class="error" id="address-missing" style="display: none;">Indirizzo di spedizione o fatturazione 
        mancante</div>
        <div class="order-button">
            <input type="submit" value="Procedi all'acquisto" />
        </div>
    </form>
</div>

</div>
<div class="cart-summary">
    <h3>Riepilogo Carrello</h3>
    <%
        if(cart != null && !cart.isEmpty()) {
    %>
    <table class="cart-table">
        <thead>
            <tr>
                <th>Prodotto</th>
                <th>Taglia</th>
                <th>Quantità</th>
                <th>Prezzo</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (CartItemBean item : cart.getCart()) {
            %>
            <tr>
                <td class="product-name"><%=item.getName()%></td>
                <td><%=item.getSize()%></td>
                <td><%=item.getQuantity()%></td>
                <td><%=String.format("%.2f", item.getPrice()) %>€</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <div class="subtotal-cost">
        <p>Subtotale: <%= String.format("%.2f", cart.getTotalPrice()) %>€</p>
    </div>
    <% } %>
</div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>