<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<%
	String email = (String) request.getSession().getAttribute("logmail");
	String username = (String) request.getSession().getAttribute("username");
	boolean isLogged = false;
	if(email != null && !email.isEmpty()){
    	isLogged = true;
	} else {
    	response.sendRedirect(request.getContextPath() + "/resources/common/Login.jsp");
    	return;
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ordini</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/cart.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<%	OrderDS ds = new OrderDS();
		Collection<OrderBean> orders = ds.doRetrieveByUserID(UserDS.getIDByMail(email));
		Iterator<?> list = orders.iterator();
		if(list.hasNext()){
			while(list.hasNext()){
				OrderBean order = (OrderBean) list.next();
	%>
	<div class="cart-container">
        <div class="prod-container">
                <h3 class="section-title">Ordine #<%= order.getId() %></h3>
                <div class="responsive-wrapper">
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Cognome</th>
                            <th>Email</th>
                            <th>Indirizzo di spedizione</th>
                            <th>Indirizzo di fatturazione</th>
                            <th>Numero di carta di credito</th>
                            <th>Totale</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<tr>
                    		<td><%=order.getUserName()%></td>
                        	<td><%=order.getUserSurname()%></td>
                        	<td><%=order.getUserEmail()%></td>
                        	<td><%=order.getShipping().toString()%></td>
                        	<td><%=order.getBilling().toString()%></td>
                        	<td><%="***********" + order.getCard().getCardNumber()
                        		.substring(order.getCard().getCardNumber().length() - 4) %>
							</td>
                        	<td><%=String.format("%.2f", order.getTotal())%>€</td>
                     	<tr>
                   	</tbody>
               	</table>
               	</div>
               	<div class="prod-container">
                <h3 class="section-title">Articoli</h3>
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Taglia</th>
                            <th>Quantità</th>
                            <th>Costo</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<% 
                    		Iterator<?> it = order.getItems().iterator(); 
                    	   	while(it.hasNext() ){
                    	   	OrderItemBean item = (OrderItemBean) it.next();
                    	   	String price = String.format("%.2f", item.getProductPrice());
                    	%>
                    	<tr>
                    		<td><%=item.getProductName()%></td>
                        	<td><%=item.getProductSize()%></td>
                        	<td><%=item.getQuantity()%></td>
                        	<td><%= item.getQuantity() + " * " + price + "€" %></td>
                     	<tr>
                     	<% } %>
                   	</tbody>
               	</table>
        </div>
        </div>
    </div>
	<% }} else { %>
                <div class="empty-cart">Non sono presenti prodotti.</div>
    <% } %>
    <%@ include file="footer.jsp" %>
</body>
</html>