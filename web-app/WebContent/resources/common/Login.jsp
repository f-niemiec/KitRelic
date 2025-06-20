<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accedi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <div class="container">
        <form class="form" action="" method="post">
            <div class="header">
                <h2>Login</h2>
                <p>Effettua l'accesso</p>
            </div>
            
            <div class="group">
                <label for="email">Email</label>
                <div class="input-wrapper">
                    <input type="email" name="email" id="email" placeholder="E-mail">
                </div>
            </div>
        
            <div class="group">
                <label for="password-one">Password</label>
                <div class="input-wrapper">
                    <input type="password" name="password" id="password-one" placeholder="Password">
                </div>
            </div>
           
            
            <input type="submit" value="Accedi">
            
            <div class="footer">
                Non hai un account? <a href="Registration.jsp">Registrati</a>
            </div>
        </form>
    </div>

</body>
</html>