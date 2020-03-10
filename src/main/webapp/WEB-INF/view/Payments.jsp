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
  <title>Student Portal - Payments</title>
</head>
<body>
<%@include file="sidebar.jsp"%>
<div class="main">
    <title-medium>Hello <g>${current_user.student_firstname}</g>, welcome to your payments page!</title-medium>
    <div class="main-content">
      <sub-label>Your current balance is €<g>${current_user.amount_paid}</g>.</sub-label><br><br>
      <sub-label>You have €<g>${current_user.remainingPay(fees)}</g> in fees left to pay.</sub-label><br><br>
      <form:form method = "post" action="/update_balance" modelAttribute="fees_input">
          <sub-label>You can pay your fees here:</sub-label><br><br>
          <sub-label for="fees_input">€ </sub-label>
          <input type="text" id="fees_input" name="fees_input" class="input-box" placeholder="Amount"><br><br>
          <input type="submit" value="Submit" class="button">
      </form:form>
    </div>
</div>
</body>
</html>