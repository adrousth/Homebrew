<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-center">
    <h1>Welcome</h1>
    <p class="h4">Current Home page for <a href="#">the</a> website</p>
</div>
<c:if test="${user == null}">
    <c:import url="login.jsp"/>
</c:if>