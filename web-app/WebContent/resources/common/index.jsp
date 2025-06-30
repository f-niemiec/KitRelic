<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.ProductBean"%>
<%
    Collection<?> products = (Collection<?>) request.getAttribute("products");
    Collection<?> novita = (Collection<?>) request.getAttribute("novita");
    Collection<?> trend = (Collection<?>) request.getAttribute("trend");
    if (products == null || novita == null || trend == null) {
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="product-container">
        <div class="section-block">
            <h3 class="section-title">Novità</h3>
            <div class="product-row">
                <%
                    for (Object obj : novita) {
                        ProductBean bean = (ProductBean) obj;
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
                    }
                %>
            </div>
        </div>
        <div class="section-block">
            <h3 class="section-title">Di tendenza</h3>
            <div class="product-row">
                <%
                    for (Object obj : trend) {
                        ProductBean bean = (ProductBean) obj;
                %>
                <div class="product-card">
                    <div class="img-wrapper">
                        <img src="ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
                    </div>
                    <span class="product-name"><%=bean.getName()%></span>
                    <span class="product-size"><%=bean.getSize()%></span>
                    <span class="product-price"><%=bean.getPrice()%>€</span>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <div class="section-block">
            <h3 class="section-title">Tutti i prodotti</h3>
            <div class="product-row">
                <%
                    for (Object obj : products) {
                        ProductBean bean = (ProductBean) obj;
                %>
                <div class="product-card">
                    <div class="img-wrapper">
                        <img src="ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
                    </div>
                    <span class="product-name"><%=bean.getName()%></span>
                    <span class="product-size"><%=bean.getSize()%></span>
                    <span class="product-price"><%=bean.getPrice()%>€</span>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
