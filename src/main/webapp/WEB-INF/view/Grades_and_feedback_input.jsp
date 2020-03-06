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
<link rel="stylesheet" href='<a:url value ="/resources/css/sidebar.css"/>' type="text/css"/>
    <title>Staff Dashboard</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <h1>Grade input Wizard</h1>
    <ul>
        <c:forEach items="${modules}" var="module">
            <li>
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
                                                    <label for="Percentage">Percentage</label>
                                                    <input type="text" name="Percentage" id="Percentage">
                                                    <label for="Letter">Letter Grade</label>
                                                    <input type="text" name="Letter" id="Letter">
                                                    <input type="submit">
                                                </form:form>
                                            </div>
                                        </li>
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
