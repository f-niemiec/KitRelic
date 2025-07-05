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
	<table class="catalogo">
		<tr>
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
			<td><img src="ViewPhotoServlet?id=<%=bean.getId()%>" onerror="this.src='./images/logo.png'"></td>
			<td><%=bean.getId()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td><%=bean.getType()%></td>
			<td><%=bean.getSize()%></td>
			<td><%=bean.getPrice()%></td>
			<td><%=bean.getQuantity()%></td>
			<td><%=bean.isTrend()%></td>
			<td><%=bean.isRecent()%></td>
			<td>
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
</body>
</html>