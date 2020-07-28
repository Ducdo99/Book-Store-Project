<%-- 
    Document   : search
    Created on : Jun 26, 2020, 1:49:55 PM
    Author     : MinhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.LASTNAME}"/>
        <c:if test="${not empty fullname}"> 
            <c:set var="roleAdmin" value="${sessionScope.ROLE}"/>
            <c:if test="${not empty roleAdmin and roleAdmin eq true}">
                <jsp:include page="welcomeUser.jsp" flush="true"/>
                <h1>Search Page</h1>  
                <form action="searchAccount"> 
                    <input type="text" name="txtSearchValue" 
                           value="${param.txtSearchValue}" 
                           placeholder="Search value..."/> 
                    <input type="submit" value="Search" name="btAction" />
                </form> 
                <c:set var="searchValueParam" value="${param.txtSearchValue}"/> 
                <c:if test="${not empty searchValueParam}">
                    <c:set var="searchValue" value="${requestScope.SEARCHVALUE}"/> 
                    <c:if test="${not empty searchValue}"> 
                        <br/>
                        <table border="1"> 
                            <thead> 
                                <tr>
                                    <th>No.</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th>Full name</th>
                                    <th>Admin</th>
                                    <th>Delete</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="updateError" 
                                       value="${requestScope.UPDATEERRORS}"/> 
                                <c:set var="usernameUpdateError" 
                                       value="${requestScope.USERNAME_UPDATE}"/> 
                                <c:set var="deleteError" 
                                       value="${requestScope.DELETEERRORS}"/>
                                <c:set var="usernameDeleteError" 
                                       value="${requestScope.USERNAME_DELETE}"/>
                                <c:forEach var="accountDTO" items="${searchValue}" 
                                           varStatus="counter"> 
                                <form action="updateAccount" method="POST">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                            .</td>
                                        <td>
                                            ${accountDTO.username} 
                                            <input type="hidden" 
                                                   name="txtUsername" 
                                                   value="${accountDTO.username}" /> 
                                        </td>
                                        <td>
                                            <input type="text" name="txtPassword" 
                                                   value="${accountDTO.password}" 
                                                   required=""/> 
                                        </td>
                                        <td>
                                            ${accountDTO.lastname}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkAdmin" 
                                                   value="ON" 
                                                   <c:if test="${accountDTO.isAdmin}">
                                                       checked="checked"
                                                   </c:if>
                                                   />
                                        </td>
                                        <td> 
                                            <c:url var="deleteLink" 
                                                   value="deleteAccount"> 
                                                <c:param name="btAction" 
                                                         value="Delete"/>
                                                <c:param name="pk" 
                                                         value="${accountDTO.username}"/>
                                                <c:param name="lastSearchValue" 
                                                         value="${searchValueParam}"/>                                            
                                            </c:url> 
                                            <a href="${deleteLink}">Delete</a>
                                        </td> 
                                        <td> 
                                            <input type="hidden" 
                                                   name="txtLastSearchValue" 
                                                   value="${searchValueParam}" />
                                            <input type="submit" value="Update" 
                                                   name="btAction" />
                                        </td>
                                    </tr> 
                                    <c:if test="${not empty deleteError.accountSignedInDeleteErr}">  
                                        <c:if test="${usernameDeleteError eq accountDTO.username}">
                                            <tr>
                                                <td colspan="7">
                                                    <font color="red">
                                                    ${deleteError.accountSignedInDeleteErr} <br/>
                                                    </font>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${not empty updateError.passwordLengthErr}"> 
                                        <c:if test="${usernameUpdateError eq accountDTO.username}">
                                            <tr>
                                                <td colspan="7">
                                                    <font color="red"> 
                                                    ${updateError.passwordLengthErr} <br/>
                                                    </font>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:if> 
                                </form> 
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty searchValue}">
                    <h3>
                        <font color="red"> 
                        Not Found!!!
                        </font>
                    </h3>
                </c:if>
            </c:if>
            </c:if>
            <c:if test="${not empty roleAdmin and roleAdmin eq false}">
                <c:redirect url="loadBooks"/>
            </c:if>
        </c:if>
        <c:if test="${empty fullname}">
            <c:redirect url="loginPage"/>
        </c:if>
    </body>
</html>
