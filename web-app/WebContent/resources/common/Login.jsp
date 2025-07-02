<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accedi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
	<script src="${pageContext.request.contextPath}/resources/scripts/login.js" defer></script>
</head>
<body>
	<%
    	if (session.getAttribute("logtype") != null && session.getAttribute("logmail") != null && 
    			session.getAttribute("logname") != null) {
        	response.sendRedirect("footer.jsp"); 
        	return;
    	}
		String fromCart = request.getParameter("fromCart");
		if(fromCart == null){
			fromCart = "false";
		}
	%>
	<div id="app-root" data-context-path="${pageContext.request.contextPath}"></div>
    <div class="container">
        <form class="form" action="${pageContext.request.contextPath}/LoginServlet" method="post" onsubmit="return validateLogin()">
            <div class="header">
                <h2>Login</h2>
                <p>Effettua l'accesso</p>
            </div>
            
            <div class="group">
                <label for="email">Email</label>
                <div class="input-wrapper">
                    <input type="email" name="email" id="email" placeholder="E-mail" onchange="checkExistence()" required>
                </div>
                 <div id="error-mailExists" class="error">Non esiste alcun account con questa mail.</div>
            </div>
        
            <div class="group">
                <label for="password-one">Password</label>
                <div class="input-wrapper">
                    <input type="password" name="password" id="password-one" placeholder="Password" required> 
                    <%
    					String error = request.getParameter("error");
                		boolean showError = "true".equals(error);
    					if (showError) {
					%>
    				<div id="error-password" class="error" style="display: block">Credenziali errate.</div>
					<%
    					}
					%>
					<div id="error-checked" class="error"></div>
					
                </div>
            </div>
           
            
            <input type="submit" id="invio" value="Accedi">
            
            <div class="footer">
                Non hai un account? <a href="${pageContext.request.contextPath}/resources/common/Registration.jsp">Registrati</a>
            </div>
        </form>
    </div>
</body>
</html>