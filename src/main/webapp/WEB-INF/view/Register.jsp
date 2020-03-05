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
<%--    <link rel="stylesheet" type="text/css" href="../../resources/css/sidebar.css">--%>
    <title>Register Page</title>

</head>
<body>

<%@include file="sidebar.jsp" %>
<br><br>
<div class="main">
    <c:if test="${flag==true}">
        <h3>Please ensure that you enter the correct values when registering!</h3>
    </c:if>
    <br><br>
    <form:form method="post"
               action="save" modelAttribute="student" name="RegisterForm">
        First name:<br>
        <form:input path="student_firstname"/><br>
        Last name:<br>
        <form:input path="student_surname"/><br>
        Email address:<br>
        <form:input path="email"/><br><br>
        Password:<br>
        <form:input path="password"/><sub>Password must contain atleast 1 letter, atleast one number and be between 8-20 characters</sub><br><br>
        Student Number:<br>
        <form:input path="student_id"/><br><br>
        Gender:<br>
        <form:input path="gender"/><br><br>
        Date of Birth:<br>
        <form:input path="dob"/><br><sub>Must be in format YEAR-MONTH-DATE <b> e.g. 25th December 2020 -> 2020-12-25 </b></sub><br>
        Stage:<br>
        <form:input path="stage"/><br><sub>Stage or current year in your course. <b> e.g. Stage 4</b></sub><br>
        Home Address:<br>
        <form:input path="address"/><br><br>
        Phone Number:<br>
        <form:input path="phone_number"/><br><br>
        <input type="submit" value="Submit">
    </form:form>
</div>
</body>
</html>
