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
<head>  <link rel="stylesheet" type="text/css" href="../../resources/css/sidebar.css"></head>
<body>
<script type = "text/javascript">

</script>
<div class="sidebar">
    <ul>
        <c:choose>
            <c:when test="${current_user==null && current_staff==null}">
                <a href="/">Home</a>
                <a href="Register">Register</a>
                <a href="Login">Student Login</a>
                <a href="StaffLogin">Staff Login</a>
                </c:when>
            <c:when test="${current_user!=null && current_staff==null}">
                <a href="Home">Home</a>
                <a href="studentDashboard">Dashboard</a>
                <a href="StudentGradesAndFeedbackPage">Grades</a>
                <a href="StudentModule">Modules</a>
                <a href="Payments">Payments</a>
                <a href="logout">Logout</a>
            </c:when>
            <c:when test="${current_user==null && current_staff!=null}">
                <a href="StaffDashboard">Dashboard</a>
                <a href="StaffModule">Grades And Feedback</a>
                <a href="logout">Logout</a>
            </c:when>
        </c:choose>
    </ul>
</div>


</body>
</html>
