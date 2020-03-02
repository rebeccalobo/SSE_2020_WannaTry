<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 02/03/2020
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css"/>' type="text/css"/>
    <title>Staff Dashboard</title>
</head>
<body>
<%@include file="StaffSideBar.jsp"%>
<div class="main">
    <h1>Hello ${current_staff.staff_firstname}, Welcome to your dashboard</h1>
</div>


</body>
</html>
