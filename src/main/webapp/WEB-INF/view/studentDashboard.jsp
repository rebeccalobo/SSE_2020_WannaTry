<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 15/02/2020
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css"/>' type="text/css"/>--%>

    <title>${current_user.student_firstname}s Dashboard</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <h1>${current_user.student_firstname}'s Dashboard</h1>
    <br>
    <br>
    <h2>Your Details</h2>
    <h6>Name: ${current_user.student_firstname} ${current_user.student_surname}
        <br>
        ID: ${current_user.student_id}
        <br>
        Gender: ${current_user.gender}
        <br>
        Stage: ${current_user.stage}
        <br>
        Email: ${current_user.email}
        <br>
        Home Address: ${current_user.address}
        <br>
    </h6>
    <h2>Your Modules</h2>
    <ul>
        <c:forEach items="${modules}" var="module">
            <li>${module.module_name}</li>
        </c:forEach>
    </ul>
</div>


</body>
</html>
