<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 02/03/2020
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href='<a:url value ="/resources/css/Main.css"/>' type="text/css"/>
    <title>Staff Dashboard</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <title-medium>Welcome to the <g>Grades</g> & <g>Feedback</g> wizard</title-medium>
    <div class="main-content">
      <c:forEach items="${modules}" var="module">
        <div class="main-content">
            <sub-label><g>${module.module_id}</g></sub-label><br>
            <sub-label><s-b>${module.module_name}</s-b></sub-label><br><br>
            <sub-label><g>Enrolled Students:</g></sub-label><br><br>
            <c:forEach items="${hashmap.get(module.module_id)}" var="student">
                <div class="content-outline">
                    <sub-label><sub-color>Student ID: </sub-color>${student}</sub-label><br>
                    <form:form action="submit_grade" method="post" class="button-and-input">
                        <input type="hidden" id = "student" name = "student" value="${student}">
                        <input type="hidden" id = "module" name = "module" value="${module.module_id}">
                        <input type="number" step="0.01" name="percentage" id="percentage" class='input-box' placeholder="Percentage Grade">
                        <input type="submit" class='button' value="Submit">
                    </form:form>
                </div><br>
            </c:forEach>
        </div><br><br>
      </c:forEach>
    </div>

<!--
    <ul>
        <c:forEach items="${modules}" var="module">
                <button type="button" class="collapsible">${module.module_name}</button>
                <div class="content">
                    <ul>
                        <li>
                            <button type="button" class="collapsible">Enrolled Students</button>
                            <div class = "content">
                                <ul>
                                    <c:forEach items="${hashmap.get(module.module_name)}" var="student">
                                        <li>
                                            <button type="button" class="collapsible">${student}</button>
                                            <div class="content">
                                                <form:form action="submit_grade" method="post">
                                                    <input type="hidden" id = "student" name = "student" value="${student}">
                                                    <input type="hidden" id = "module" name = "module" value="${module.module_id}">
                                                    <input type="text" name="Percentage" id="Percentage" class='input-box' placeholder="Percentage Grade">
                                                    <input type="text" name="Letter" id="Letter" class="input-box" placeholder="Letter Grade">
                                                    <input type="submit" class='button'>
                                                </form:form>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
        </c:forEach>
    </ul></!-->
</div>
<!--
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
</!-->
</body>
</html>
