<%@ page import="com.SSE2020.WannaTry.model.Students" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 15/02/2020
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head> <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css?ver=<?php echo rand(111,999)?>"/>' type="text/css"/></head>
<body>
<div class="sidebar">
    <ul>

        <c:choose>
            <c:when test="${current_user==null}">
                <li><a href="/">Home</a></li>
                <li><a href="Register">Register</a> </li>
                <li><a href="Login">Login</a></li></c:when>
            <c:otherwise>
                <li><a href="Home">Home</a></li>
                <li><a href="studentDashboard">Dashboard</a></li>
                <li><a href="StudentGradesAndFeedbackPage">Grades</a></li>
                <li><a href="StudentModule">Modules</a></li>
                <li><a href="Payments">Payments</a></li>
                <li><a href="logout">Logout</a> </li></c:otherwise>
        </c:choose>
    </ul>
</div>


</body>
</html>
