<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modifica prodotto</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/upload.css">
	
</head>
<body>
 <div class="container">
        <form class="form" action="${pageContext.request.contextPath}/ModifyProductServlet" method="post">
            <div class="header">
                <h2>Modifica prodotto</h2>
                <p>Inserisci le informazioni da modificare</p>
            </div>
                <div class="group">
                    <label for="name">Nome</label>
                    <div class="input-wrapper">
                        <input type="text" name="name" id="name" placeholder="Nome prodotto">
                    </div>
                </div>

				<div class="group">
                    <label for="description">Descrizione</label>
                    <div class="input-wrapper">
                        <textarea name="description" id="description" placeholder="Descrizione del prodotto" rows="6"></textarea>
                    </div>
                </div>
                
                <div class="group">
                    <label for="quantity">Quantità</label>
                    <div class="input-wrapper">
                        <input type="number" id="quantity" name="quantity" placeholder="1" min="1">
                    </div>
                </div>
             <div class="group">
                    <label for="price">Prezzo in euro</label>
                    <div class="input-wrapper">
                        <input type="number" name="prezzo" step="0.01" min="0" placeholder="15">
                    </div>
                </div>            
            <div class="group checkbox-row">
  				<div class="checkbox-col">
   					<label for="trend">Spunta per invertire il campo "Tendenza"</label>
    				<input type="checkbox" name="trend" id="trend" value="true">
  				</div>
  				<div class="checkbox-col">
    				<label for="recent">Spunta per invertire il campo "Nuovo"</label>
    				<input type="checkbox" name="recent" id="recent" value="true">
  				</div>
			</div> 
			<%
    			String id = request.getParameter("id");
    			String action = request.getParameter("action");
			%>
			<input type="hidden" name="id" value="<%= id %>">
			<input type="hidden" name="action" value="<%= action %>">

		<input type="submit" id="confirm" value="Conferma">
	</form>
</div>

</body>
</html>