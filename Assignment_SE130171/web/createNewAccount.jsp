<%-- 
    Document   : createNewAccount
    Created on : Jul 10, 2020, 1:14:05 PM
    Author     : MinhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New</title>
    </head>
    <body>
        <h1>Create New Account</h1> 
        <form action="registerAccount" method="POST">  
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            <input type="text" name="txtUsername" 
                   value="${param.txtUsername}" 
                   placeholder="Username..." 
                   />* (6 - 20 characters) <br/> 
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr} <br/> 
                </font>
            </c:if>
            <c:if test="${not empty errors.usernamBeDisable}">
                <font color="red">
                ${errors.usernamBeDisable} <br/> 
                </font>
            </c:if><br/>
            <input type="password" name="txtPassword" 
                   value="" 
                   placeholder="Password..." 
                   />* (8 - 30 characters) <br/> 
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr} <br/> 
                </font>
            </c:if> <br/>
            <input type="password" name="txtConfirmPassword" 
                   value="" 
                   placeholder="Confirm..."
                   />* <br/> 
            <c:if test="${not empty errors.confirmNotMatchedErr}">
                <font color="red">
                ${errors.confirmNotMatchedErr} <br/>
                </font>
            </c:if>
            <br/>
            <input type="text" name="txtFullName" 
                   value="${param.txtFullName}" 
                   placeholder="Full name..." 
                   />* (2 - 50 characters) <br/> 
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red">
                ${errors.fullnameLengthErr} <br/> 
                </font>
            </c:if><br/>
            <jsp:include page="reCaptcha.html" flush="true"/>
            <c:if test="${not empty errors.doNotClickOnReCaptchaErr}"> 
                <font color="red">
                ${errors.doNotClickOnReCaptchaErr} <br/>
                </font>
            </c:if> <br/>
            <input type="submit" value="Create New Account" name="btAction" /> 
            <input type="reset" value="Reset" /> <br/> 
            <c:if test="${not empty errors.usernamIsExisted}">
                <font color="red">
                ${errors.usernamIsExisted}
                </font>
            </c:if>
        </form>
    </body>
</html>
