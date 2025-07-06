<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Collection, model.*"%>
<%
    String email = (String) request.getSession().getAttribute("logmail");
    boolean isLogged = false;
    boolean fromCart = true;
    if(email != null && !email.isEmpty()){
        isLogged = true;
    } else {
        response.sendRedirect(request.getContextPath() + "/resources/common/Login.jsp?fromCart=" + fromCart);
        return;
    }
    Collection<AddressBean> billing = null;
    Collection<AddressBean> shipping = null;
    Collection<PaymentCardBean> cards = null;
    AddressDS ds = new AddressDS();
    PaymentCardDS pc = new PaymentCardDS();
    int userId = 0;
    if(isLogged){
        userId = UserDS.getIDByMail(email);
        request.getSession().setAttribute("userID", userId);
        billing = ds.doRetrieveBilling(userId);
        shipping = ds.doRetrieveShipping(userId);
        cards = pc.doRetrieveByUserID(userId);
        session.setAttribute("billingList", billing);
        session.setAttribute("shippingList", shipping);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="${pageContext.request.contextPath}/resources/scripts/checkout.js" defer></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/info.css">
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="page-container">
        <div class="section-row">
            <div class="section-box">
                <h3 class="section-title">Indirizzi di spedizione</h3>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Città</th>
                            <th>Via</th>
                            <th>Provincia</th>
                            <th>Paese</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if(shipping != null && !shipping.isEmpty()){
                                for(AddressBean a : shipping){
                        %>
                        <tr>
                            <td><%=a.getCity()%></td>
                            <td><%=a.getStreet()%></td>
                            <td><%=a.getProvince()%></td>
                            <td><%=a.getCountry()%></td>           
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="5">Non è presente alcun indirizzo di spedizione.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="section-box">
                <h3 class="section-title">Indirizzi di fatturazione</h3>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Città</th>
                            <th>Via</th>
                            <th>Provincia</th>
                            <th>Paese</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if(billing != null && !billing.isEmpty()){
                                for(AddressBean a : billing){
                        %>
                        <tr>
                            <td><%=a.getCity()%></td>
                            <td><%=a.getStreet()%></td>
                            <td><%=a.getProvince()%></td>
                            <td><%=a.getCountry()%></td>      
                        </tr>
                        <%
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="5">Non è presente alcun indirizzo di fatturazione.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="form-wrapper">
    <div class="section-box narrow-box">
        <h3 class="section-title">Aggiungi un nuovo indirizzo</h3>
        <form action="<%=request.getContextPath()%>/AddAddressServlet" method="post" class="address-form" onsubmit="return validateCheckout(event)">
            <input type="hidden" name="userID" value="<%=userId%>">
            <input type="hidden" name="fromInfo" value="true">
            <label for="addressType">Tipo:</label>
            <select name="addressType" required>
                <option value="Billing">Fatturazione</option>
                <option value="Shipping">Spedizione</option>
            </select>
            <label for="city">Città:</label>
            <input type="text" name="city" id="city" required>
            <div class="error" id="error-city" style="display: none;">Città non valida</div>
            <label for="street">Via:</label>
            <input type="text" name="street" id="street" required>
            <div class="error" id="error-street" style="display: none;">Via non valida</div>
            <label for="province">Provincia:</label>
            <input type="text" name="province" id="province" required>
            <div class="error" id="error-province" style="display: none;">Provincia non valida</div>
            <label for="country">Paese:</label>
            <input type="text" name="country" id="country" required>
            <div class="error" id="error-country" style="display: none;">Paese non valido</div>
            <input type="submit" value="Aggiungi indirizzo">
        </form>
    </div>
</div>
        <div class="section-box">
            <h3 class="section-title">Carte di pagamento</h3>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>Numero</th>
                        <th>Proprietario</th>
                        <th>Scadenza</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if(cards != null && !cards.isEmpty()){
                            for(PaymentCardBean c : cards){
                    %>
                    <tr>
                        <td>**** **** **** <%=c.getCardNumber().substring(c.getCardNumber().length()-4)%></td>
                        <td><%=c.getOwner()%></td>
                        <td><%=c.getExpires()%></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4">Non è presente alcuna carta di pagamento.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>