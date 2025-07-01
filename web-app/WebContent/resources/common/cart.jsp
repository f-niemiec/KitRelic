<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, model.ProductBean, model.Cart, model.CartItemBean"%>
<%
    ProductBean products = (ProductBean) request.getAttribute("products");    
    String email = (String) request.getSession().getAttribute("logmail");
    String username = (String) request.getSession().getAttribute("logname");
    boolean isLogged = false;
    if(email != null && !email.isEmpty()){
        isLogged = true;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/cart.css">
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="cart-container">
        <div class="prod-container">
            <% if(isLogged) { %>
                <h3 class="section-title">Carrello di <%=username%></h3>
            <% } else { %>
                <h3 class="section-title">Carrello</h3>
            <% } %>
            <% if (!cart.isEmpty()) { %>
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Prodotto</th>
                            <th>Nome</th>
                            <th>Taglia</th>
                            <th>Quantità</th>
                            <th>Prezzo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% Iterator<?> it = cart.getCart().iterator();
                           while (it.hasNext()) {
                            CartItemBean bean = (CartItemBean) it.next(); %>
                        <tr>
                            <td>
                                <div class="img-wrapper">
                                    <img src="${pageContext.request.contextPath}/ViewPhotoServlet?id=<%=bean.getId()%>" alt="<%=bean.getName()%>">
                                </div>
                            </td>
                            <td class="product-name"><%=bean.getName()%></td>
                            <td><%=bean.getSize()%></td>
                            <td> <%= bean.getQuantity() %> </td>
                            <td><%= String.format("%.2f", bean.getPrice()) %>€</td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <div class="product-actions">
                                    <form action="${pageContext.request.contextPath}/CartControlServlet" method="post">
                                        <input type="hidden" name="fromCart" value="true">
                                        <input type="hidden" name="action" value="increment">
                                        <input type="hidden" name="itemId" value="<%=bean.getId()%>">
                                        <button type="submit" class="increment" title="Aumenta quantità">
                                            <i class="fa-solid fa-plus"></i>
                                        </button>
                                    </form>
                                    <% if(bean.getQuantity() > 1) {%>
                                    <form action="${pageContext.request.contextPath}/CartControlServlet" method="post">
                                        <input type="hidden" name="fromCart" value="true">
                                        <input type="hidden" name="action" value="decrement">
                                        <input type="hidden" name="itemId" value="<%=bean.getId()%>">
                                        <button type="submit" class="decrement" title="Diminuisci quantità">
                                            <i class="fa-solid fa-minus"></i>
                                        </button>
                                    </form>
                                    <% } %>
                                    <form action="${pageContext.request.contextPath}/CartControlServlet" method="post">
                                        <input type="hidden" name="fromCart" value="true">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="itemId" value="<%=bean.getId()%>">
                                        <button type="submit" class="remove" title="Rimuovi prodotto">
                                            <i class="fa-solid fa-trash"></i>
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <form action="${pageContext.request.contextPath}/CartControlServlet" method="post" class="clear-cart-form">
                    <input type="hidden" name="fromCart" value="true">
                    <input type="hidden" name="action" value="clear">
                    <input type="submit" value="Svuota carrello">
                </form>
            <% } else { %>
                <div class="empty-cart">Non sono presenti prodotti.</div>
            <% } %>
        </div>
        <div class="recap">
            <h2 class="recap-name">Il tuo ordine</h2>
            <div class="shipping">
                <p class="shipping-cost">Costo di spedizione stimato: GRATIS</p>
            </div>
            <div class="subtotal-cost">
                <p class="subtotal">Subtotale: <%=String.format("%.2f", cart.getTotalPrice()) %>€</p> 
            </div>
            <div class="total-cost">
                <p class="total">Totale: <%=String.format("%.2f", cart.getTotalPrice()+0)%>€</p> 
            </div>
            <% if (!cart.isEmpty()) { %>
            <div class="checkout">
                <form action="${pageContext.request.contextPath}/" method="post">
                    <input type="submit" class="invio" value="Procedi al checkout">
                </form>
            </div>
            <% } %>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
