<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${results.success}">
        <div class="text-left alert-info center-block img-rounded" id="message">
    </c:when>
    <c:otherwise>
        <div class="text-left alert-danger center-block img-rounded" id="message">
    </c:otherwise>
</c:choose>
    <h4 class="text-center">${results.type}</h4>
    <ul>
        <c:forEach items="${results.messages}" var="line">
            <li>${line}</li>
        </c:forEach>
    </ul>
</div>