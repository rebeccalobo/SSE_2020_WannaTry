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
    <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css"/>' type="text/css"/>

    <title>${current_user.student_firstname}s Dashboard</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <h1>${current_user.student_firstname}s Dashboard</h1>
    Student ID: ${current_user.student_id}
    <br>
    Student name: ${current_user.student_firstname} ${current_user.student_surname}
</div>


</body>
</html>
