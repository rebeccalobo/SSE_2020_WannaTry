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
    <link rel="stylesheet" type="text/css" href="../../resources/css/Main.css">
    <title>Student Portal - Modules</title>
</head>
<body>
  <%@include file="sidebar.jsp"%>
  <div class="main">
    <title-medium>My Modules</title-medium>
    <div class="main-content">
      <c:forEach items="${modules}" var="module">
        <div class="main-content">
            <sub-label><g>${module.module_id}</g></sub-label><br>
            <sub-label><s-b>${module.module_name}</s-b></sub-label><br><br>
            <sub-label><g>Module Description:</g></sub-label><br>
            <sub-label>${module.description}</sub-label><br><br>
            <sub-label><g>Module Duration:</g></sub-label><br>
            <sub-label><sub-color>${module.start_date} to ${module.end_date}</sub-color></sub-label>
          </div><br>
      </c:forEach>
    </div>
  </div>

<div class="available"></div>

</body>
</html>