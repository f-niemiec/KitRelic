<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrazione</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<form class="form" action="" method="post" >
	<h2>Registrazione</h2>
	<p>Nome</p>
	<input type="text" class="name" id="name" placeholder="Nome">
	<p>Cognome</p>
	<input type="text" class="surname" id="surname" placeholder="Cognome">
	<p>Email</p>
	<input type="email" class="email" id="email" placeholder="E-mail">
	<p>Data di nascita</p>
	<input type="date" class="date" id="date"> <br>
	<input type="submit" class="submit" value="Invio">
</form>

</body>
</html>