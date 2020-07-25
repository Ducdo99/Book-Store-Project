<%-- 
    Document   : confirm
    Created on : Jul 12, 2020, 11:32:35 AM
    Author     : MinhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm</title>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART}"/> 
        <c:if test="${not empty cart}"> 
            <c:set var="books" value="${cart.items}"/> 
            <c:set var="checkoutError" value="${requestScope.CHECKOUT_ERROR}"/>
            <c:if test="${not empty books}"> 
                <h1>Your Cart</h1> 
                <form action="checkout" method="POST">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Book Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
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
                                        <input type="hidden" name="txtBookName" 
                                               value="${book.value.bookName}" />
                                    </td>
                                    <td>
                                        ${book.value.quantity}
                                    </td>
                                    <td>
                                        ${book.value.price}
                                    </td>
                                    <td>
                                        ${book.value.quantity * book.value.price}
                                    </td>
                                </tr> 
                            </c:forEach>  
                        </tbody>
                    </table> 
                    <input type="text" name="txtReceiverName" 
                           value="${param.txtReceiverName}" 
                           placeholder="Receiver name..."
                           required=""/> <br/> 
                    <c:if test="${not empty checkoutError.receiverNameLengthErr}">
                        <font color="red"> 
                        ${checkoutError.receiverNameLengthErr} <br/>
                        </font>
                    </c:if>
                    <input type="text" name="txtReceiverAddress" 
                           value="${param.txtReceiverAddress}" 
                           placeholder="Receiver address..."
                           required=""/> <br/> 
                    <c:if test="${not empty checkoutError.receiverAddressLengthErr}">
                        <font color="red"> 
                        ${checkoutError.receiverAddressLengthErr} <br/>
                        </font>
                    </c:if>
                    <input type="submit" value="OK" 
                           name="btAction" />
                </form>
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
