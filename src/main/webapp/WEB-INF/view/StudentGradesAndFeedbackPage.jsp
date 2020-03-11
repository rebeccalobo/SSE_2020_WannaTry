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
    <title>Student Portal - Grades</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <title-medium>My <g>Grades</g> & <g>Feedback</g> area</title-medium>
    <div class="main-content">
    	<sub-label>Module grades will appear below once the module has been <g>completed</g> (<i>i.e. when its end date has passed</i>)</sub-label><br><br>
	    <c:forEach items="${modules}" var="module">
	        <div class="main-content">
	            <sub-label><g>${module.module_id}</g></sub-label><br>
	            <sub-label><s-b>${module.module_name}</s-b></sub-label><br><br>
	            <sub-label><g>Grade %:</g></sub-label><br>
	            <sub-label><g>69</g></sub-label><br>
	            <sub-label>Letter Grade:</sub-label><br>
	            <sub-label><g>69</g></sub-label><br>
	        </div><br>
		</c:forEach>
  </div>
</div>

<div class="available"></div>

</body>
</html>
