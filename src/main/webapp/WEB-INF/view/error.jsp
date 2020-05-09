<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.SSE2020.WannaTry.model.CustomUserDetails" %>
<%@ page import="com.SSE2020.WannaTry.model.User" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 23/04/2020
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/Main.css">
    <title>Error</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class ="form-container">
    <h1>Looks like there was an error </h1>
    <p>
        An error has occurred.<br><br>
        <c:out value="${error_found}"/>
    </p>


</div>


</body>
</html>
