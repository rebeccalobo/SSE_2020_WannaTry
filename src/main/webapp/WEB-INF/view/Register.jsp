<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 10/02/2020
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/Main.css">
    <title>Registration Page</title>
</head>
<body>

<%@include file="sidebar.jsp" %>
<div class="form-container">
    <c:if test="${incorrectFormat==correct}">
        <error-text>Please ensure that you enter the correct values when registering!</error-text>
    </c:if>
    <title-medium>User Registration</title-medium>
    <form:form method="post" action="save" modelAttribute="User" name="RegisterForm" class='main-content'>
        <form:input path="FName" class='input-box' placeholder='First Name'/><br><br>
        <form:input path="LName" class='input-box' placeholder='Surname'/><br><br>
        <form:input path="email" class='input-box' placeholder='Email Address' type="email"/><br><br>
        <sub-label>Password must contain at least 1 letter, at least 1 number and be between 8-20 characters</sub-label><br><br>
        <form:input path="password" class='input-box' placeholder='Password' type="password"/><br><br>
        <form:input path="address" class='input-box' placeholder='Address'/><br><br>
        <sub-label>Must be in format YEAR-MONTH-DATE <i>e.g. 25th December 2020 -> 2020-12-25</i></sub-label><br><br>
        <form:input path="DOB" class='input-box' placeholder='Date of Birth'/><br><br>
        <form:input path="phoneNumber" class='input-box' placeholder='Phone Number' type="number"/><br><br>
        <form:input path="ethnicity" class='input-box' placeholder='Ethnicity'/><br><br>
        <sub-label>MALE = M, FEMALE = F, OTHER = O</sub-label><br><br>
        <form:input path="gender" class='input-box' placeholder='Gender'/><br><br>
        <input type="submit" class='button' value="Submit">
    </form:form>
</div>
</body>
</html>
