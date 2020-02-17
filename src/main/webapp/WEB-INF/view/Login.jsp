<%--

  User: kiowa
  Date: 09/02/2020
  Time: 21:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="NavBar.jsp"%>

<form:form method="post" action="login_user" modelAttribute="user">
    ID:<br>
    <form:input path="student_id"/><br>
    Password:<br>
    <form:input path="password"/><br>
    <input type="submit" value="Login">
</form:form>
</body>
</html>