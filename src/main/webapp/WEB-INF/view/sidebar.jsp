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
<head> <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css?ver=<?php echo rand(111,999)?>"/>' type="text/css"/></head>
<body>
<script type = "text/javascript">

</script>
<div class="sidebar">
    <ul>

        <c:choose>
            <c:when test="${current_user==null && current_staff==null}">
                <li><a href="/">Home</a></li>
                <li><a href="Register">Register</a> </li>
                <li><a href="Login">Student Login</a></li>
                <li><a href="StaffLogin">Staff Login</a></li>
                </c:when>
            <c:when test="${current_user!=null && current_staff==null}">
                <li><a href="Home">Home</a></li>
                <li><a href="studentDashboard">Dashboard</a></li>
                <li><a href="StudentGradesAndFeedbackPage">Grades</a></li>
                <li><a href="StudentModule">Modules</a></li>
                <li><a href="Payments">Payments</a></li>
                <li><a href="logout">Logout</a> </li>
                <li><form action="unregister" class="confirmation"><input type="submit" value="delete" onclick="return confirm('Are you sure you want to unregister')"></form></li>
            </c:when>
            <c:when test="${current_user==null && current_staff!=null}">
                <li><a href="StaffDashboard">Dashboard</a></li>
                <li><a href="StaffModule">Grades And Feedback</a></li>
                <li><a href="logout">Logout</a> </li>
            </c:when>
        </c:choose>
    </ul>
</div>


</body>
</html>
