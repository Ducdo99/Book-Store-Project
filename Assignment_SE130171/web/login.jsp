<%-- 
    Document   : login
    Created on : Jul 27, 2020, 3:26:25 PM
    Author     : MinhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

    </head>
    <body>
        <h1>Login Page</h1>
        <c:set var="signInError" value="${requestScope.SIGN_IN_ERRORS}"/>
        <form action="login" method="POST"> 
            <input type="text" name="txtUsername" value="${param.txtUsername}" 
                   placeholder="Username..." 
                   required=""/> <br/> <br/>
            <input type="password" name="txtPassword" value="" 
                   placeholder="Password..." 
                   required=""/> <br/>  <br/>
            <jsp:include page="reCaptcha.html" flush="true"/>
            <c:if test="${not empty signInError.doNotClickOnReCaptchaErr}"> 
                <font color="red">
                ${signInError.doNotClickOnReCaptchaErr} <br/>
                </font>
            </c:if>
            <br/>
            <input type="checkbox" name="addCookie" 
                   value="true" /> Remember this account <br/> <br/>
            <input type="submit" value="Sign In" 
                   name="btAction"/> 
            <input type="reset" value="Reset" /> <br/> <br/>
        </form> 
        <c:if test="${not empty signInError.incorrectUsernameOrPasswordErr}"> 
            <font color="red">
            ${signInError.incorrectUsernameOrPasswordErr} <br/> <br/>
            </font>
        </c:if>
        <a href="registerPage">Click here to Sign Up</a> 
        <br/> <br/>
        <a href="loadBooks">Shopping Store</a> 
    </body>
</html>
