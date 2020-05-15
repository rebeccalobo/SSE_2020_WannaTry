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
    <title-medium>Hello <g>${current_user.FName}</g>, welcome to your payments page!</title-medium>
    <div class="main-content">
        <c:if test="${invalid == true}">
            <p><g>Please select a number that is less than or equal to the fees due</g></p>
        </c:if>
      <sub-label>You have €<g>${fees_due}</g> in fees left to pay.</sub-label><br><br>
      <form:form method = "post" action="/update_balance" >
          <sub-label>You can pay your fees here:</sub-label><br><br>
          <sub-label for="fees_input">€ </sub-label>
          <input type="number" step="0.01" min="0.00" id="fees_input" name="fees_input" class="input-box" placeholder="Amount" required ><br><br>
          <input type="submit" value="Submit" class="button">
      </form:form>
    </div>
</div>
</body>
</html>