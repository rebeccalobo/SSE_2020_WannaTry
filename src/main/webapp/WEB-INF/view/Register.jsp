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
        <form:input path="FName" class='input-box' placeholder='First Name' required="required" pattern="[A-Za-z]+" title="Please enter your first name"/><br><br>
        <form:input path="LName" class='input-box' placeholder='Surname' required="required" pattern="[A-Za-z]+" title="Please enter your surname"/><br><br>
        <form:input path="email" class='input-box' placeholder='Email Address' type="email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter your email"/><br><br>
        <sub-label>Password must be at least <g>8</g> characters, containing at least <g>1</g> number, at least <g>1</g> upper <g>AND</g> lowercase letter</sub-label><br><br>
        <form:input path="password" class='input-box' placeholder='Password' type="password" required="required" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Password must meet the requirements"/><br><br>
        <form:input path="address" class='input-box' placeholder='Address' required="required" pattern="[a-zA-Z0-9 ]+" title="Please enter your address"/><br><br>
        <sub-label>Must be in format <g>YEAR-MONTH-DATE</g> <i>e.g. 25th December 2020 -> <g>2020-12-25</g></i></sub-label><br><br>
        <form:input path="DOB" class='input-box' placeholder='Date of Birth' required="required" pattern="^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$" title="Please use the required format"/><br><br>
        <form:input path="phoneNumber" class='input-box' placeholder='Phone Number' type="number" required="required" pattern="{,20}" title="Please enter your phone number"/><br><br>
        <sub-label>Select Ethnicity</sub-label><br><br>
        <form:select path="ethnicity" class='input-box' placeholder='Ethnicity'>
            <form:option value="White">White</form:option>
            <form:option value="Black or African American">Black</form:option>
            <form:option value="American Indian or Alaska Native">Native American</form:option>
            <form:option value="Asian">Asian</form:option>
            <form:option value="African">African</form:option>
        </form:select><br><br>
        <sub-label>Select Gender</sub-label><br><br>
        <form:select path="gender" class='input-box' placeholder='Gender' >
            <form:option value="M">Male</form:option>
            <form:option value="F">Female</form:option>
            <form:option value="O">Other</form:option>
        </form:select><br><br>
        <input type="submit" class='button' value="Submit">
    </form:form>
</div>
</body>
</html>
