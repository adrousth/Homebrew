<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:choose>
    <c:when test="${assetType == \"hop\"}">
        <c:set var="admin" value="${user.containsRole(\"HOP CZAR\")}"/>
        <c:set var="unit" value="oz"/>
        <h2 class="text-center">Available Hops</h2>
    </c:when>
    <c:when test="${assetType == \"grain\"}">
        <c:set var="admin" value="${user.containsRole(\"GRAIN CZAR\")}"/>
        <c:set var="unit" value="lbs"/>
        <h2 class="text-center">Available Grains</h2>
    </c:when>
</c:choose>
<table class="center-block table table-striped" id="list">

    <c:forEach var="asset" items="${assets}" varStatus="loopStatus">
        <c:if test="${loopStatus.index % 2 == 0}">
            <tr>
        </c:if>

        <td>
            <h3 class="text-capitalize">${asset.name}</h3>
            <c:if test="${admin}">
                <a href="${pageContext.request.contextPath}/admin/${assetType}/${asset.assetId}">update</a>
            </c:if>
            <p>Current stock: <b>${asset.currentStock} ${unit}</b></p>
            <p>${asset.description}</p>
        </td>
        <c:if test="${loopStatus.index % 2 == 1 || loopStatus.last}">
            </tr>
        </c:if>
    </c:forEach>
</table>