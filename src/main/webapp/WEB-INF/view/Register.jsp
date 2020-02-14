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
    <link rel="stylesheet" type="text/css" href="../../resources/css/Home.css">
    <title>Register Page</title>

</head>
<body>
    <ul>
        <li><a href="Home">Home</a></li>
        <li><a href="Register">Register</a></li>
    </ul>

    <form name="register">
        First name:<br>
        <input type="text" name="firstname"><br>
        Last name:<br>
        <input type="text" name="lastname"><br><br>
        Email address:<br>
        <input type="text" name="email"><br><br>
        Student Number:<br>
        <input type="text" name="studentnumber"><br><br>
        Address:<br>
        <input type="text" name="address"><br><br>
        Phone Number:<br>
        <input type="text" name="phonenumber"><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
