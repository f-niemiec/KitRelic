<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.ProductBean"%>
<%
    ProductBean product = (ProductBean) request.getAttribute("productBean");
	Collection<?> novita = (Collection<?>) request.getAttribute("novita");
	Collection<?> trend = (Collection<?>) request.getAttribute("trend");
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/product.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="product-viewer">
		<div class="productImage-wrap">
			<img src="ViewPhotoServlet?id=<%=product.getId()%>" alt="<%=product.getName()%>">
		</div>
		<div class="productBox">
			<% if(product.isRecent()) { %>
			<div class="newProduct"><span class="extra">Novità</span></div>
			<% } %>
			<% if(product.isTrend()) { %>
			<div class="newProduct"><span class="extra">Di tendenza</span></div>
			<% } %>
			<h2 class="productTitle"><%=product.getName()%></h2>
			<span class="productDescription"><%=product.getDescription()%></span>
			<span class="productSize"><%=product.getSize()%></span>
			<span class="productPrice"><%= String.format("%.2f", product.getPrice()) %>€</span>
			<% if(product.getQuantity() > 0) { %>
			<form action="${pageContext.request.contextPath}/CartControlServlet" method="post" class="cartForm">
				<input type="hidden" name="action" value="add">
				<input type="hidden" name="productId" value="<%= product.getId() %>">
				<label for="quantity" class="quantity">Quantità:</label>
				<input type="number" name="quantity" id="quantity" class="quantitySelect" min="1" value="1" placeholder="1" max="<%=product.getQuantity()%>">
				<div class="cartButton">
					<input type="submit" class="addToCart" value="Aggiungi al carrello">
				</div>
			</form>
			<% } else { %>
			<div class="cartButton">
				<button class="dontCart">Prodotto esaurito</button>
			</div>
			<% } %>
		</div>
	</div>
	<%if(product.isTrend() && trend!=null){%>
	<div class="product-container">
		<div class="section-block">
        	<h3 class="section-title">Altri prodotti di tendenza</h3>
            <div class="product-row">
                <%
                    for (Object obj : trend) {
                        ProductBean bean = (ProductBean) obj;
                        if(bean.getId()!= product.getId()){
                %>
                <div class="product-card">
                	<a href="ProductPage?prodId=<%=bean.getId()%>" class="product-card-link">
                    	<div class="img-wrapper">
                        	<img src="ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
                    	</div>
                    	<span class="product-name"><%=bean.getName()%></span>
                    	<span class="product-size"><%=bean.getSize()%></span>
                    	<span class="product-price"><%= String.format("%.2f", bean.getPrice()) %>€</span>
                </a>
                </div>
                <%
                        }}
                %>
            </div>
        </div>
    </div>
        <% } if(product.isRecent() && novita!=null){%>
	<div class="product-container">
		<div class="section-block">
        	<h3 class="section-title">Altri prodotti aggiunti di recente</h3>
            <div class="product-row">
                <%
                    for (Object obj : trend) {
                        ProductBean bean = (ProductBean) obj;
                        if(bean.getId()!= product.getId()){
                %>
                <div class="product-card">
                	<a href="ProductPage?prodId=<%=bean.getId()%>" class="product-card-link">
                    	<div class="img-wrapper">
                        	<img src="ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
                    	</div>
                    	<span class="product-name"><%=bean.getName()%></span>
                    	<span class="product-size"><%=bean.getSize()%></span>
                    	<span class="product-price"><%= String.format("%.2f", bean.getPrice()) %>€</span>
                </a>
                </div>
                <%
                        }}
                %>
            </div>
        </div>
    </div> 
    <% } %>
	<%@ include file="footer.jsp" %>
</body>
</html>
