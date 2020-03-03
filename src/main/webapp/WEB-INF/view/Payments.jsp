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
<%--    <link rel="stylesheet" href='<c:url value ="/resources/css/sidebar.css"/>' type="text/css"/>--%>
    <title>Title</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <h1>Hello ${current_user.student_firstname}, welcome to your payments page!</h1>
    <h4>Your current balance is € ${current_user.amount_paid}.</h4>
    <h6>You have € ${current_user.remainingPay()} in fees to pay.</h6>

    <form:form method = "post" action="/update_balance" modelAttribute="fees_input">
        <h6>You can pay your fees here</h6>
        <br>
        <label for="fees_input"> € </label>
        <input type="text" id="fees_input" name="fees_input"><br><br>
        <input type="submit" value="Submit">
    </form:form>

</div>
</body>
</html>