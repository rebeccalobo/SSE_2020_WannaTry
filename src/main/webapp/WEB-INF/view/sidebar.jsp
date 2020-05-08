<%@ page import="com.SSE2020.WannaTry.model.Students" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 15/02/2020
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>  <link rel="stylesheet" type="text/css" href="../../resources/css/sidebar.css">
    <title>Springfield University</title></head>
<body>
<script type = "text/javascript">

</script>
<div class="sidebar">
    <ul>
        <c:choose>
            <c:when test="${flag == true}">
                <a href="">Home</a>
                <a href="Register">Register</a>
                <a href="Login">Login</a>
                </c:when>
            <c:otherwise>
                <a href="Home">Home</a>
                <a href="Dashboard">Dashboard</a>
                <a href="grades">Grades</a>
                <a href="StudentModule">Modules</a>
                <a href="logout">Logout</a>
            </c:otherwise>
        </c:choose>
    </ul>
</div>


</body>
</html>
