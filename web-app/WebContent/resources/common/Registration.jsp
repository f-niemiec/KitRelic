<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrazione</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
    <div class="container">
        <form class="form" action="" method="post">
            <div class="header">
                <h2>Registrazione</h2>
                <p>Crea il tuo account</p>
            </div>
            
                <div class="group">
                    <label for="name">Nome</label>
                    <div class="input-wrapper">
                        <input type="text" id="name" placeholder="Nome">
                    </div>
                </div>

            
            <div class="group">
                <label for="email">Email</label>
                <div class="input-wrapper">
                    <input type="email" id="email" placeholder="E-mail">
                </div>
            </div>
            
            <div class="group">
                <label for="date">Data di nascita</label>
                <div class="input-wrapper">
                    <input type="date" id="date">
                </div>
            </div>
            
            <div class="group">
                <label for="password-one">Password</label>
                <div class="input-wrapper">
                    <input type="password" id="password-one" placeholder="Password">
                </div>
            </div>
            
            <div class="group">
                <label for="password-two">Conferma Password</label>
                <div class="input-wrapper">
                    <input type="password" id="password-two" placeholder="Conferma password">
                </div>
            </div>
            
            <input type="submit" value="Registrati">
            
            <div class="footer">
                Hai già un account? <a href="Login.jsp">Accedi</a>
            </div>
        </form>
    </div>

</body>
</html>