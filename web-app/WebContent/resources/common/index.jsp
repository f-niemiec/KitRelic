<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.ProductBean"%>
<%
    Collection<?> products = (Collection<?>) request.getAttribute("products");
	Collection<?> novita = (Collection<?>) request.getAttribute("novita");
	Collection<?> trend = (Collection<?>) request.getAttribute("trend");
    if(products == null || novita == null || trend == null) {
        response.sendRedirect(request.getContextPath() + "/ProductControlServlet");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>KitRelic</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="product-container">
		<div class="new-products">
			<%
				if (novita != null && novita.size() != 0) {
					Iterator<?> it = novita.iterator();
					while (it.hasNext()) {
						ProductBean bean = (ProductBean) it.next();
			%>
			<div class="product">
				<img src="ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
				<span class="product-name"><%=bean.getName()%></span>
				<span class="product-size"><%=bean.getSize()%></span>
				<span class="product-price"><%=bean.getPrice()%>€</span>
			</div>
			<%
					}
				}
			%>
		</div>
		<div class="trend-products">
			<%
				if (trend != null && trend.size() != 0) {
					Iterator<?> it = trend.iterator();
					while (it.hasNext()) {
						ProductBean bean = (ProductBean) it.next();
			%>
			<div class="product">
				<img src="ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
				<span class="product-name"><%=bean.getName()%></span>
				<span class="product-size"><%=bean.getSize()%></span>
				<span class="product-price"><%=bean.getPrice()%>€</span>
			</div>
			<%
					}
				}
			%>
		</div>
		<div class="all-products">
			<%
				if (products != null && products.size() != 0) {
					Iterator<?> it = products.iterator();
					while (it.hasNext()) {
						ProductBean bean = (ProductBean) it.next();
			%>
			<div class="product">
				<img src="ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
				<span class="product-name"><%=bean.getName()%></span>
				<span class="product-size"><%=bean.getSize()%></span>
				<span class="product-price"><%=bean.getPrice()%>€</span>
			</div>
			<%
					}
				}
			%>
		</div>
	
	
	</div>
</body>
</html>