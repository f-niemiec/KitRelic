<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*, control.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
	<div class="general-header">
		<a href="#"><img class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
		<div class="general-content">
			<div class="icons">
								<i class="fa-solid fa-cart-shopping"></i>
				<div class="dropdown">
					<button class="dropdown-menu"><i class="fa-solid fa-circle-user"></i></button>
					<div class ="dropdown-content">
						<% if(!user) { %>
						<a href="${pageContext.request.contextPath}/resources/admin/catalogue.jsp">Catalogo</a>
						<a href="${pageContext.request.contextPath}/resources/admin/productUpload.jsp">Aggiunta prodotti</a>
						<a href="#">Storico ordini</a>		
						<% } else if(mail!=null){ %>
						<a href="#">Storico ordini</a>
						<% } else { %>
						<a href="${pageContext.request.contextPath}/resources/common/Login.jsp">Login</a>
						<a href="${pageContext.request.contextPath}/resources/common/Registration.jsp">Registrazione</a>
						<% } %>
						<a href="${pageContext.request.contextPath}/LogoutServlet"></a>
					</div>
					
				</div>
			</div>
		
			
			<% if(!user ) { %>
			<span class="private-area">Area admin</span>
			<% } else if(mail!=null) { %>
			<span class="private-area">Area personale</span>
			<% } %>
		</div>
	</div>
</body>
</html>