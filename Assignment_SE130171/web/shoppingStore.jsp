<%-- 
    Document   : shoppingStore
    Created on : Jun 29, 2020, 4:12:04 PM
    Author     : MinhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body> 
        <jsp:include page="welcomeUser.jsp" flush="true"/>
        <h1>Shopping Store</h1> 
        <c:set var="bookList" value="${requestScope.BOOKLIST}"/> 
        <c:if test="${not empty bookList}"> 
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Book Name</th>
                        <th>Price</th>
                        <th>Order</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="book" items="${bookList}" varStatus="counter">
                    <form action="addToCart">
                        <tr>
                            <td> 
                                ${counter.count}
                                .</td>
                            <td>
                                ${book.bookName} 
                                <input type="hidden" 
                                       name="txtBookName" 
                                       value="${book.bookName}" />
                            </td>
                            <td>
                                ${book.price} 
                                <input type="hidden" 
                                       name="txtPrice" 
                                       value="${book.price}" />
                            </td>
                            <td>
                                <input type="submit" 
                                       value="Add to your cart" 
                                       name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>       
            </tbody>
        </table>
    </c:if>
    <form action="viewCartPage"> 
        <input type="submit" value="View Your Cart" name="btAction" />
    </form> 
</body>
</html>
