<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 15/02/2020
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/Main.css">
    <title>Student Dashboard</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <title-medium><g>${current_user.FName}</g>'s Dashboard</title-medium>
    <div class="main-content">
      <sub-label>Full Name: <g>${current_user.FName} ${current_user.LName}</g></sub-label><br><br>
      <sub-label>Student ID: <g>${current_user.ID}</g></sub-label><br><br>
      <sub-label>Gender: <g>${current_user.gender}</g></sub-label><br><br>
      <sub-label>Email Address: <g>${current_user.email}</g></sub-label><br><br>
      <sub-label>Home Address: <g>${current_user.address}</g></sub-label><br><br>
    </div><br><br>
    <c:if test="${fees_paid == false}">
        <form:form method="get" action="/Payments" class='main-content'>
            <input type="submit" class='button' value="Pay Fees">
        </form:form>
    </c:if>
    <form action="unregister" class="confirmation">
      <input type="submit" value="Unregister" onclick="return confirm('Are you sure you want to unregister?')" class="button-red">
    </form>
</div>

</body>
</html>
