<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 02/03/2020
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="../../resources/css/Main.css">
  <title>Staff Login</title>
</head>

<body>
<%@include file="sidebar.jsp"%>
<div class="form-container">    
    <title-medium>Staff Login</title-medium>
    <form:form method="post" action="login_staff" modelAttribute="staff" class='main-content'>
      <form:input path="staff_id" class='input-box' placeholder='Staff ID'/><br><br>
      <c:if test="${error!=null}">
        <error-text>${error}</error-text><br><br>
      </c:if>
      <form:input path="password" class='input-box' placeholder='Password'/><br><br>
      <c:if test="${password_ok == false}">
        <error-text>Incorrect password, please try again!</error-text><br><br>
      </c:if>
      <input type="submit" class='button' value="Login">
    </form:form>
</div>


</body>

</html>
