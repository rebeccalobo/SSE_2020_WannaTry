<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<link rel="stylesheet" href='<x:url value ="/resources/css/sidebar.css"/>' type="text/css"/>
    <title>Staff Dashboard</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <h1>Hello ${current_staff.staff_firstname}, Welcome to your dashboard</h1>
    <ul>
        <c:forEach items="${modules}" var="module">
            <li>
                <button type="button" class="collapsible">${module.module_name}</button>
                <div class="content">
                    <ul>
                        <li>
                            <button type="button" class="collapsible">Description</button>
                            <div class = "content">
                                <p>${module.description}</p>
                                <button type="button" class="collapsible">Edit description</button>
                                <div class="content">
                                    <form:form method="post" action="edit_description">
                                        <input type="text" style="height: 50px" name="description" id="description">
                                        <input type="hidden" name="module_id" id="module_id" value="${module.module_id}">
                                        <input type="submit">
                                    </form:form>
                                </div>
                            </div>
                        </li>
                        <li>
                            <button type="button" class="collapsible">Module Duration</button>
                            <div class = "content">
                                <p>This module runs during the period between ${module.start_date} and ${module.end_date}</p>
                            </div>
                        </li>
                        <li>
                            <button type="button" class="collapsible">Enrolled Students</button>
                            <div class = "content">
                                <ul>
                                    <c:forEach items="${hashmap.get(module.module_name)}" var="student">
                                        <li>${student}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<script>
    var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    }
</script>

</body>
</html>
