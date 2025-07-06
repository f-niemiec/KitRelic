<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.*"%>
<%
    Collection<?> orders = (Collection<?>) request.getAttribute("orders");
    if(orders == null){
        response.sendRedirect(request.getContextPath() + "/ViewOrdersServlet");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tutti gli ordini</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tables.css">
</head>
<body>
	<h2>Bentornato, ${sessionScope.logname}! </h2>
	<p>Seleziona l'azione che desideri compiere.</p>
	<div class="container">
		<div class="order">
			<button onclick="window.location.href='${pageContext.request.contextPath}/resources/common/index.jsp'">Home</button>
		</div>
		<div class="insert">
			<button onclick="window.location.href='${pageContext.request.contextPath}/resources/admin/productUpload.jsp'">Inserisci prodotto</button>
		</div>
		<div class="order">
			<button onclick="window.location.href='${pageContext.request.contextPath}/resources/admin/catalogue.jsp'">Catalogo</button>
		</div>
	</div>
    <h2>Ordini Utente</h2>
    <form action="FilterOrdersServlet" method="post" class="filter-form">
    <fieldset>
        <legend>Filtra Ordini</legend>
        <div class="form-group">
            <label for="from">Data inizio</label>
            <input type="date" id="from" name="from">
        </div>
        <div class="form-group">
            <label for="to">Data fine</label>
            <input type="date" id="to" name="to">
        </div>
        <div class="form-group">
            <label for="userId">ID Utente</label>
            <input type="number" id="userId" name="userId" min="1">
        </div>
        <input type="submit" value="Filtra">
    </fieldset>
</form>
    
    <div class="order-container">
    <%
        Iterator<?> list = orders.iterator();
        if(list.hasNext()){
            while(list.hasNext()){
                OrderBean order = (OrderBean) list.next();
    %>
    <p class="order-number">Ordine #<%=order.getId()%>, Data: <%=order.getDate()%></p>
    <table class="catalogo">
        <thead>
            <tr>
                <th>ID Utente</th>
                <th>Email</th>
                <th>Indirizzo di spedizione</th>
                <th>Indirizzo di fatturazione</th>
                <th>Numero di carta</th>
                <th>Totale</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td data-label="ID Utente"><%=order.getUserId()%></td>
                <td data-label="Email"><%=order.getUserEmail()%></td>
                <td data-label="Indirizzo di spedizione"><%=order.getShipping().toString()%></td>
                <td data-label="Indirizzo di fatturazione"><%=order.getBilling().toString()%></td>
                <td data-label="Numero di carta"><%="***********" + order.getCard().getCardNumber().substring(order.getCard().getCardNumber().length() - 4)%></td>
                <td data-label="Totale"><%=String.format("%.2f", order.getTotal())%>€</td>
                <td></td>
            </tr>
            <tr class="colonne">
                <th>Nome Prodotto</th>
                <th>Taglia</th>
                <th>Quantità</th>
                <th>Prezzo unitario</th>
                <th>Prezzo totale</th>
                <th colspan="2"></th>
            </tr>
            <%
                Iterator<?> it = order.getItems().iterator();
                while(it.hasNext()){
                    OrderItemBean item = (OrderItemBean) it.next();
                    String price = String.format("%.2f", item.getProductPrice());
                    String total = String.format("%.2f", item.getProductPrice() * item.getQuantity());
            %>
            <tr>
                <td data-label="Nome Prodotto"><%=item.getProductName()%></td>
                <td data-label="Taglia"><%=item.getProductSize()%></td>
                <td data-label="Quantità"><%=item.getQuantity()%></td>
                <td data-label="Prezzo unitario"><%=price%>€</td>
                <td data-label="Prezzo totale"><%=total%>€</td>
                <td colspan="2"></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <br/>
    <% }} else { %>
        <div class="empty-cart">Nessun ordine disponibile.</div>
    <% } %>
    </div>
</body>
</html>
