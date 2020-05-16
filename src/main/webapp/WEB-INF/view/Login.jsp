<%--

  User: kiowa
  Date: 09/02/2020
  Time: 21:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/Main.css">
    <title>Student Login</title>
</head>
<body>
    <%@include file="sidebar.jsp"%>

    <div class="form-container">
        <title-medium>User Login</title-medium>
        <form:form method="post"  class='main-content'>
            <c:if test="${failed_attempt == true}"><error-text>Username or Password incorrect</error-text></c:if><br><br>
            <input type ="number" class='input-box' placeholder='Staff / Student ID' name="id" pattern="{,10}"><br><br>
            <c:if test="${error!=null}">
                <error-text>${error}</error-text><br><br>
            </c:if>
            <input path="password" class='input-box' placeholder='Password' type="password" name="pwd"/><br><br>
            <c:if test="${password_ok == false}">
                <error-text>Incorrect password, please try again!</error-text><br><br>
            </c:if>
            <input type="submit" class='button' value="Login">
        </form:form>
    </div>
</body>
</html>