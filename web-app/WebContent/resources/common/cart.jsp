<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.ProductBean, model.Cart, model.CartItemBean"%>
<%
	ProductBean products = (ProductBean) request.getAttribute("products");
	Cart cart = (Cart) request.getSession().getAttribute("cart");
	if(cart == null){
		cart = new Cart();
		request.getSession().setAttribute("cart", cart);
	}
	
	String email = (String) request.getSession().getAttribute("logmail");
	String name = (String) request.getSession().getAttribute("logname");
	boolean isLogged = false;
	if(email != null && !email.isEmpty()){
		isLogged = true;
	}
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Carrello</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="cart-container">
		<div class="prod-container">
			<% if(isLogged) {%>
			<h3 class="section-title">Carrello di <%=name%></h3>
			<% } else {%>
			<h3 class="section-title">Carrello</h3>
			<% } %>
			<% if (!cart.isEmpty()){ %>
			<div class="cart-products">
				<% Iterator<?> it = cart.getCart().iterator();
				   while (it.hasNext()) {
				   		CartItemBean bean = (CartItemBean) it.next();%>
				<div class="img-wrapper">
                	<img src="${pageContext.request.contextPath}/ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
                </div>
                <span class="product-name"><%=bean.getName()%></span>
                <span class="product-size"><%=bean.getSize()%></span>
                <span class="product-price"><%= String.format("%.2f", bean.getPrice()) %>€</span>
                <span class="product-quantity">Quantità: <%= bean.getQuantity()%></span>
                <% if (bean.getQuantity() > 1) {%>
              	<form action="${pageContext.request.contextPath}/CartControlServlet" method="post">
              		 <input type="hidden" name="fromCart" value="true">
                	 <input type="hidden" name="action" value="increment">
                     <input type="hidden" name="itemId" value="<%=bean.getId()%>">
                     <button type="submit" class="increment">
                     	<i class="fa-solid fa-plus"></i>
                     </button>
                </form>
                <form action="${pageContext.request.contextPath}/CartControlServlet" method="post">
                	 <input type="hidden" name="fromCart" value="true">
                	 <input type="hidden" name="action" value="decrement">
                     <input type="hidden" name="itemId" value="<%=bean.getId()%>">
                     <button type="submit" class="decrement">
                     	<i class="fa-solid fa-minus"></i>
                     </button>
                </form>
                <%  } %>
                <form action="${pageContext.request.contextPath}/CartControlServlet" method="post">
                	 <input type="hidden" name="fromCart" value="true">
                	 <input type="hidden" name="action" value="delete">
                     <input type="hidden" name="itemId" value="<%=bean.getId()%>">
                     <button type="submit" class="remove">
                     	<i class="fa-solid fa-trash"></i>
                     </button>
                </form>
                <% } %>
                <form action="${pageContext.request.contextPath}/CartControlServlet" method="post">
                	 <input type="hidden" name="fromCart" value="true">
                	 <input type="hidden" name="action" value="clear">
                     <input type="submit" value="Svuota carrello">
                </form>
			</div>
			<% } else {%>
				<div class="empty-cart">Non sono presenti prodotti.</div>
			<% } %>
		</div>
		<div class="recap">
			<h2 class="recap-name">Il tuo ordine</h2>
			<div class="shipping">
				<p class="shipping-cost">Costo di spedizione stimato: GRATIS</p>
			</div>
			<div class="subtotal-cost">
				<p class="subtotal">Subtotale: <%=String.format("%.2f", cart.getTotalPrice()) %>€</p> 
			</div>
			<div class="total-cost">
				<p class="total">Totale: <%=String.format("%.2f", cart.getTotalPrice()+0)%>€</p> 
			</div>
		</div>
	</div>
</body>
</html>