<%@ page import="com.SSE2020.WannaTry.model.Students" %>
<%@ taglib prefix="b" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kiowa
  Date: 15/02/2020
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head> <link rel="stylesheet" href='<b:url value ="/resources/css/sidebar.css?ver=<?php echo rand(111,999)?>"/>' type="text/css"/></head>
<body>
<script type = "text/javascript">

</script>
<div class="sidebar">
    <ul>
                <li><a href="StaffDashboard">Dashboard</a></li>
                <li><a href="StaffModule">My Modules</a></li>
                <li><a href="logout">Logout</a> </li>
    </ul>
</div>


</body>
</html>
