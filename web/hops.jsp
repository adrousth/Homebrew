<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2 class="text-center">Available Hops</h2>
<table class="center-block table table-striped" id="list">

    <c:forEach var="hop" items="${hops}" varStatus="loopStatus">
        <c:if test="${loopStatus.index % 2 == 0}">
            <tr>
        </c:if>

            <td>
            <h3 class="text-capitalize">${hop.name}</h3>
                <c:if test="${user.containsRole(\"ADMIN\")}">
                    <a href="#">update</a>
                </c:if>
                <p>Current stock: <b>${hop.currentStock}oz</b></p>
                <p>${hop.description}</p>
            </td>
        <c:if test="${loopStatus.index % 2 == 1}">
            </tr>
        </c:if>
    </c:forEach>
</table>