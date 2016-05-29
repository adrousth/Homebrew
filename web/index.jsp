<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 4/4/2016
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="head.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
</head>
<body>

<c:import url="navbar-header.jsp"/>

<div class="container-fluid">

    <div class="row">

        <c:import url="navbar.jsp"/>

        <div class="col-sm-12" id="main">
            <c:if test="${not empty results}">
                <c:import url="message.jsp"/>
            </c:if>

            <c:import url="${pageContent}"/>
        </div>
    </div>

</div>
<c:import url="footer.jsp"/>
</body>
</html>

