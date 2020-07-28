<%-- 
    Document   : welcomeUser
    Created on : Jul 27, 2020, 4:11:50 PM
    Author     : MinhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.LASTNAME}"/>
        <c:if test="${not empty fullname}"> 
            <form action="logout" method="POST"> 
                <h2>
                    <font color="red"> 
                    Welcome, ${fullname}
                    </font> 
                </h2>
                <input type="submit" value="Sign Out" name="btAction" />
            </form>
        </c:if>
        <c:if test="${empty fullname}">
            <c:redirect url="loginPage"/>
        </c:if>
    </body>
</html>
