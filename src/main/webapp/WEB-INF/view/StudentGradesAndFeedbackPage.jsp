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
<%--    <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css"/>' type="text/css"/>--%>
    <title>Title</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">

    <h1>hello this is your grade feedback page!</h1>
    <ul>
        <c:forEach items="${grades}" var="grade">
            <li>${grade.module_id}, ${grade.percentage}, ${grade.letter_grade}</li>
        </c:forEach>
    </ul>
</div>


</body>
</html>
