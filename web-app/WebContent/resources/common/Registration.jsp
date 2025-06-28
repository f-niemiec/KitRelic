<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrazione</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
    <script src="${pageContext.request.contextPath}/resources/scripts/registration.js" defer></script>
</head>
<body>
	<%
    	if (session.getAttribute("logtype") != null && session.getAttribute("logmail") != null && 
    			session.getAttribute("logname") != null) {
        	response.sendRedirect("footer.jsp"); 
        	return;
    	}
	%>
	<div id="app-root" data-context-path="${pageContext.request.contextPath}"></div>
    <div class="container">
        <form class="form" action="${pageContext.request.contextPath}/RegistrationServlet" method="post" onsubmit="return validate(event)">
            <div class="header">
                <h2>Registrazione</h2>
                <p>Crea il tuo account</p>
            </div>
            
                <div class="group">
                    <label for="name">Nome</label>
                    <div class="input-wrapper">
                        <input type="text" name="name" id="name" placeholder="Nome" required>
                    </div>
                    <div id="error-name" class="error">Nome non valido.</div>
                </div>

				<div class="group">
                    <label for="surname">Cognome</label>
                    <div class="input-wrapper">
                        <input type="text" name="surname" id="surname" placeholder="Cognome" required>
                    </div>
                    <div id="error-surname" class="error">Cognome non valido.</div>
                </div>
            
            <div class="group">
                <label for="email">Email</label>
                <div class="input-wrapper">
                    <input type="email" name="email" id="email" placeholder="E-mail" required onkeyup="checkExistence()">
                </div>
                <div id="error-mailValid" class="error">Email non valida.</div>
                <div id="error-mailExists" class="error">Esiste già un account con la seguente mail.</div>
            </div>
            
            <div class="group">
                <label for="date">Data di nascita</label>
                <div class="input-wrapper">
                    <input type="date" name="date" id="date" required>
                </div>
                <div id="error-date" class="error">Data non valida.</div>
            </div>
            
            <div class="group">
                <label for="password-one">Password</label>
                <div class="input-wrapper">
                    <input type="password" name="password" id="password-one" placeholder="Password" required>
                </div>
                <div id="error-password" class="error">La password deve essere di 8 caratteri e contenere una lettera maiuscola, una minuscola ed un carattere speciale.</div>
            </div>
            
            <div class="group">
                <label for="password-two">Conferma Password</label>
                <div class="input-wrapper">
                    <input type="password" name="confirm" id="password-two" placeholder="Conferma password" required onchange="passwordMatch()">
                </div>
                <div id="error-match" class="error">Le password non corrispondono.</div>
            </div>
            
            <input type="submit" id="invio" value="Registrati">
            
            <div class="footer">
                Hai già un account? <a href="${pageContext.request.contextPath}/resources/common/Login.jsp">Accedi</a>
            </div>
        </form>
    </div>

</body>
</html>