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
    <c:if test="${flag==true}">
        <error-text>Please ensure that you enter the correct values when registering!</error-text>
    </c:if>
    <title-medium>Student Registration</title-medium>
    <form:form method="post" action="save" modelAttribute="student" name="RegisterForm" class='main-content'>
        <form:input path="student_firstname" class='input-box' placeholder='Name'/><br><br>
        <form:input path="student_surname" class='input-box' placeholder='Surname'/><br><br>
        <form:input path="email" class='input-box' placeholder='Email Address'/><br><br>
        <sub-label>Password must contain at least 1 letter, at least 1 number and be between 8-20 characters</sub-label><br><br>
        <form:input path="password" class='input-box' placeholder='Password'/><br><br>
        <form:input path="student_id" class='input-box' placeholder='Student Number'/><br><br>
        <form:input path="gender" class='input-box' placeholder='Gender'/><br><br>
        <sub-label>Must be in format YEAR-MONTH-DATE <i>e.g. 25th December 2020 -> 2020-12-25</i></sub-label><br><br>
        <form:input path="dob" class='input-box' placeholder='Date of Birth'/><br><br>
        <sub-label>Stage or current year of your course. <i>e.g. Stage 4</i></sub-label><br><br>
        <form:input path="stage" class='input-box' placeholder='Stage'/><br><br>
        <form:input path="address" class='input-box' placeholder='Home Address'/><br><br>
        <form:input path="phone_number" class='input-box' placeholder='Phone Number'/><br><br>
        <input type="submit" class='button' value="Submit">
    </form:form>
</div>
</body>
</html>
