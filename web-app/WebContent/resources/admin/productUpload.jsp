<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inserisci prodotto</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/upload.css">
	
</head>
<body>
 <div class="container">
        <form class="form" action="${pageContext.request.contextPath}/AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="header">
                <h2>Inserimento prodotto</h2>
                <p>Inserisci le informazioni richieste</p>
            </div>
                <div class="group">
                    <label for="name">Nome</label>
                    <div class="input-wrapper">
                        <input type="text" name="name" id="name" placeholder="Nome prodotto" required>
                    </div>
                    <div id="error-name" class="error">Nome non valido.</div>
                </div>

				<div class="group">
                    <label for="description">Descrizione</label>
                    <div class="input-wrapper">
                        <textarea name="description" id="description" placeholder="Descrizione del prodotto" rows="6" required></textarea>
                    </div>
                    <div id="error-description" class="error">Descrizione non valida.</div>
                </div>
                
                <div class="group">
                    <label for="quantity">Quantità</label>
                    <div class="input-wrapper">
                        <input type="number" id="quantity" name="quantity" placeholder="1" min="1" required>
                    </div>
                </div>
             <div class="group">
                    <label for="price">Prezzo in euro</label>
                    <div class="input-wrapper">
                        <input type="number" name="prezzo" step="0.01" min="0" placeholder="15" required>
                    </div>
                </div>
            <div class="group">
                <label for="type">Tipo</label>
                <div class="input-wrapper">
                    <input type="text" name="type" id="type" placeholder="Tipo" required>
                </div>
                <div id="error-type" class="error">Tipo non valido.</div>
            </div>
            
            <div class="group">
  			<label>Taglia</label>
  				<div class="taglie-group">
  					<label class="tag-size">
      					<input type="radio" name="taglia" value="Unica" required>
      					<span>Unica</span>
    				</label>
    				<label class="tag-size">
      					<input type="radio" name="taglia" value="XS">
      					<span>XS</span>
    				</label>
    				<label class="tag-size">
      					<input type="radio" name="taglia" value="S">
      					<span>S</span>
    				</label>
    				<label class="tag-size">
      					<input type="radio" name="taglia" value="M">
      					<span>M</span>
    				</label>
    				<label class="tag-size">
      					<input type="radio" name="taglia" value="L">
      					<span>L</span>
   					</label>
    				<label class="tag-size">
      					<input type="radio" name="taglia" value="XL">
      					<span>XL</span>
    				</label>
  				</div>
			</div>
            <div id="error-size" class="error">Seleziona una taglia.</div>
            <div class="group checkbox-row">
  				<div class="checkbox-col">
   					<label for="trend">Di tendenza</label>
    				<input type="checkbox" name="trend" id="trend" value="true">
  				</div>
  				<div class="checkbox-col">
    				<label for="recent">Nuovo arrivo</label>
    				<input type="checkbox" name="recent" id="recent" value="true">
  				</div>
			</div> 
			<div class="group">
                <label for="type">Foto</label>
                <div class="input-wrapper">
                    <input class="file" type="file" name="photo" maxlength="255" required>
                </div>
            </div>

		<input type="submit" id="confirm" value="Conferma">
	</form>
</div>

</body>
</html>