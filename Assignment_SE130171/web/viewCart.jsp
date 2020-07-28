<%-- 
    Document   : viewCart
    Created on : Jun 30, 2020, 12:59:10 PM
    Author     : MinhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <jsp:include page="welcomeUser.jsp" flush="true"/>
        <c:set var="cart" value="${sessionScope.CART}"/> 
        <c:if test="${not empty cart}"> 
            <c:set var="books" value="${cart.items}"/> 
            <c:if test="${not empty books}"> 
                <h1>Your Cart</h1> 
                <form action="removeFromCart" method="POST">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Book Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Selected</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${books}" 
                                       varStatus="counter"> 
                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${book.value.bookName} 
                                    </td>
                                    <td>
                                        ${book.value.quantity}
                                    </td>
                                    <td>
                                        ${book.value.price}
                                    </td>
                                    <td>
                                        ${book.value.price * book.value.quantity}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkBoxRemove" 
                                               value="${book.value.bookName}" 
                                               <c:if test="${not empty chkBoxRemove}">
                                                   checked="checked"
                                               </c:if> />
                                    </td> 
                                </tr> 
                            </c:forEach> 
                            <tr>
                                <td colspan="5">
                                    <a href="loadBooks"> 
                                        Shopping Store 
                                    </a>
                                </td> 
                                <td>
                                    <input type="submit" 
                                           value="Remove from your cart" 
                                           name="btAction" />
                                </td> 
                            </tr>
                        </tbody>
                    </table>
                </form>
                <form action="confirm">
                    <input type="submit" value="Check Out" 
                           name="btAction" />
                </form>
            </c:if> 
            <c:if test="${empty books}">
                <h1>
                    <font color="red"> 
                    Your cart is empty
                    </font>
                </h1> 
                <a href="loadBooks">
                    <h3>
                        Shopping Store 
                    </h3>
                </a>
            </c:if>
        </c:if> 
        <c:if test="${empty cart}"> 
            <a href="loadBooks">
                <h3>
                    Shopping Store 
                </h3>
            </a>
        </c:if>
    </body>
</html>
