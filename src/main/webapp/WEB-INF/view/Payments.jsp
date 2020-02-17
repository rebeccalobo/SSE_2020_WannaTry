<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 16/02/2020
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css"/>' type="text/css"/>
    <title>Title</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <%@include file="NavBar.jsp"%>
    hello this is your payments page!
</div>


</body>
</html>