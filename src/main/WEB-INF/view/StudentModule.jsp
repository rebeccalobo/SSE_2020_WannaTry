<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 16/02/2020
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <sub-label><sub-color>${module.start_date} to ${module.end_date}</sub-color></sub-label><br><br>
            <form:form method="post" action="un_enrol" class="confirmation">
              <input  type="hidden" id = "student_unEnrol" name = "student" value="${current_user.ID}"/>
              <input  type="hidden" id = "module_UnEnrol" name = "module" value="${module.module_id}"/>
              <input type="submit" value="Un-enrol / Drop" onclick="return confirm('Are you sure you want to drop this module?')" class="button-red">
            </form:form>
          </div><br>
      </c:forEach>
    </div>
  </div><br><br>

  <div class="main">
    <title-medium>Available Modules</title-medium>
    <div class="main-content">
      <c:forEach items="${available_modules}" var="module">
        <div class="main-content">
            <sub-label><g>${module.module_id}</g></sub-label><br>
            <sub-label><s-b>${module.module_name}</s-b></sub-label><br><br>
            <sub-label><g>Module Description:</g></sub-label><br>
            <sub-label>${module.description}</sub-label><br><br>
            <sub-label><g>Module Duration:</g></sub-label><br>
            <sub-label><sub-color>${module.start_date} to ${module.end_date}</sub-color></sub-label><br><br>
            <form:form method="post" action="enrol"  class="confirmation">
                <input  type="hidden" id = "module" name = "module" value="${module.module_id}"/>
                <input type="submit" value="Enrol" onclick="return confirm('Are you sure you want to enrol into this module?')" class="button">

            </form:form>
          </div><br>
      </c:forEach>
    </div>
  </div>

<div class="available"></div>

</body>
</html>