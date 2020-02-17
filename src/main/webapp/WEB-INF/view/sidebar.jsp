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
<head> <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css"/>' type="text/css"/></head>
<body>
<div class="sidebar">
    <ul>
        <li><a href="studentDashboard">Dashboard</a></li>
        <li><a href="StudentGradesAndFeedbackPage">Grades</a></li>
        <li><a href="StudentModule">Modules</a></li>
        <li><a href="Payments">Payments</a></li>

        <li><a href="/">Logout</a> </li>
    </ul>
</div>


</body>
</html>
