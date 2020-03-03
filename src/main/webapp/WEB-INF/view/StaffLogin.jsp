<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 02/03/2020
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff Login</title>
</head>

<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <c:if test="${error!=null}">
        <h3>${error}</h3>
    </c:if>
    <c:if test="${password_ok == false}">
        <h3> incorrect password, please try again!</h3>
    </c:if>
    <form:form method="post" action="login_staff" modelAttribute="staff">
        ID:<br>
        <form:input path="staff_id"/><br>
        Password:<br>
        <form:input path="password"/><br>
        <input type="submit" value="Login">
    </form:form>
</div>


</body>

</html>
