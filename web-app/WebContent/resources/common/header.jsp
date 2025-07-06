<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*, control.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css">
	<script src="${pageContext.request.contextPath}/resources/scripts/header.js"></script>
</head>
<body>
	<%	Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		String mail = (String) request.getSession().getAttribute("logmail");
		String name = (String) request.getSession().getAttribute("logname");
		String type = (String) request.getSession().getAttribute("logtype");
		boolean log = false;
		boolean user = true;
		if(mail!=null && !mail.isEmpty()){
			log = true;
		}
		if(type!=null && type.equalsIgnoreCase("Admin")){
			user = false;
		}
	%>
	<div id="app-root" data-context-path="${pageContext.request.contextPath}"></div>
	<div class="general-header">
		<a href="${pageContext.request.contextPath}/resources/common/index.jsp"><img class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
		
		<div class="searchbar">
			<form class="search-form" onsubmit="return false" >
   				<input type="text" name="search" placeholder="Cerca un'epoca, una squadra, un mito..." aria-label="Search">
    			<button type="submit"><i class="fa fa-search"></i></button>
			</form>
			<div id="search-results" class="search-results"></div>
		</div>
		<div class="general-content">
			<div class="icons">
			<i class="fa fa-search search-icon"></i>
			<a href="${pageContext.request.contextPath}/resources/common/cart.jsp" 
				class="cart-icon-wrapper" data-cart-size="<%= (cart != null) ? cart.getSize() : 0 %>">
				<i class="fa-solid fa-cart-shopping"></i>
				<div id="cart-count" class="cart-count"></div>
			</a>
				<div class="dropdown">
					<button class="dropdown-menu"><i class="fa-solid fa-circle-user"></i></button>
					<div class ="dropdown-content">
						<% if(!user) { %>
						<a href="${pageContext.request.contextPath}/resources/admin/catalogue.jsp">Catalogo</a>
						<a href="${pageContext.request.contextPath}/resources/admin/productUpload.jsp">Aggiunta prodotti</a>
						<a href="${pageContext.request.contextPath}/resources/admin/completed-orders.jsp">Storico ordini</a>	
						<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>	
						<% } else if(mail!=null){ %>
						<a href="${pageContext.request.contextPath}/resources/common/all-orders.jsp">Storico ordini</a>
						<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
						<% } else { %>
						<a href="${pageContext.request.contextPath}/resources/common/Login.jsp">Login</a>
						<a href="${pageContext.request.contextPath}/resources/common/Registration.jsp">Registrazione</a>
						<% } %>
					</div>
				</div>
			</div>
		
			
			<% if(!user) { %>
			<span class="private-area"><a href="${pageContext.request.contextPath}/resources/admin/catalogue.jsp">Area admin</a></span>
			<% } else if(mail!=null) { %>
			<span class="private-area"><a href="${pageContext.request.contextPath}/resources/common/info.jsp">Area personale</a></span>
			<% } %>
		</div>
	</div>
</body>
</html>