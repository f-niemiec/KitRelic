<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.ProductBean"%>
<%
    Collection<?> products = (Collection<?>) request.getAttribute("products");
    String success = request.getParameter("success");
    if (success == null) {
        Object successAttribute = request.getAttribute("success");
        if (successAttribute != null) {
            success = successAttribute.toString();
        } else {
            success = "false";
        }
    }
    if(products == null) {
        if ("true".equals(success)) {
            response.sendRedirect(request.getContextPath() + "/ViewProductServlet?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/ViewProductServlet");
        }
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Catalogo</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/tables.css">
</head>
<body>
	<h2>Bentornato, ${sessionScope.logname}! </h2>
	<p>Seleziona l'azione che desideri compiere.</p>
	<div class="container">
		<div class="order">
			<button onclick="window.location.href='${pageContext.request.contextPath}/resources/common/index.jsp'">Home</button>
		</div>
		<div class="insert">
			<button onclick="window.location.href='${pageContext.request.contextPath}/resources/admin/productUpload.jsp'">Inserisci prodotto</button>
		</div>
		<div class="order">
			<button onclick="window.location.href='${pageContext.request.contextPath}/resources/admin/completed-orders.jsp'">Visualizza ordini utente</button>
		</div>
	</div>
	<%
		boolean showSuccess = "true".equals(success);
		if (showSuccess) {
	%>
    <div id="success" class="success">Operazione completata.</div>
	<%
		}
	%>
	<h2>Prodotti</h2>
	<div class ="catalogo-wrapper">
	<table class="catalogo">
		<tr class="colonne">
			<th>Foto</th>
			<th>ID</th>
			<th>Nome</th>
			<th>Descrizione</th>
			<th>Tipo</th>
			<th>Taglia</th>
			<th>Prezzo</th>
			<th>Quantità</th>
			<th>Di tendenza</th>
			<th>Nuovo</th>
			<th>Azioni</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		<tr>
			<td data-label="Foto"><img src="ViewPhotoServlet?id=<%=bean.getId()%>" onerror="this.src='./images/logo.png'"></td>
			<td data-label="ID"><%=bean.getId()%></td>
			<td data-label="Nome"><%=bean.getName()%></td>
			<td data-label="Descrizione"><%=bean.getDescription()%></td>
			<td data-label="Tipo"><%=bean.getType()%></td>
			<td data-label="Taglia"><%=bean.getSize()%></td>
			<td data-label="Prezzo"><%=bean.getPrice()%></td>
			<td data-label="Quantità"><%=bean.getQuantity()%></td>
			<td data-label="Di tendenza"><%=bean.isTrend()%></td>
			<td data-label="Nuovo"><%=bean.isRecent()%></td>
			<td data-label="Azioni">
				<a href="${pageContext.request.contextPath}/resources/admin/productModify.jsp?action=modify&id=<%=bean.getId()%>">Modifica</a><br>
				<a href="${pageContext.request.contextPath}/ModifyProductServlet?action=delete&id=<%=bean.getId()%>">Elimina</a><br>
			</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="11">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
</body>
</html>