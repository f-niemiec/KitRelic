<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.ProductBean"%>
<%
    ProductBean product = (ProductBean) request.getAttribute("productBean");
    if(product == null){
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/resources/errors/404.jsp");
        dispatcher.forward(request, response);
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><%= product.getName()%></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="product-viewer">
		<div class="productImage-wrap">
			<img src="ViewPhotoServlet?id=<%=product.getId()%>" alt="<%=product.getName()%>">
		</div>
		<div class="productBox">
			<h2 class="productTitle"><%=product.getName()%></h2>
			<span class="productDescription"><%=product.getDescription()%></span>
			<span class="productSize"><%=product.getSize()%></span>
			<span class="productPrice"><%= String.format("%.2f", product.getPrice()) %>€</span>
		</div>
		<%	if(product.getQuantity() > 0) {%>
		<div class="cartButton">
			<button class="addToCart">Aggiungi al carrello</button>
		</div>
		<% } else {%>
		<div class="cartButton">
			<button class="wordToCart">Prodotto esaurito</button>
		</div>
		<% } %>
	</div>
</body>
</html>