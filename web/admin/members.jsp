<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="center-block table table-striped" id="list">
    <h2 class="text-center">Members</h2>
    <c:forEach var="member" items="${members}" varStatus="loopStatus">
        <c:if test="${loopStatus.index % 2 == 0}">
            <tr>
        </c:if>
        <td>
            <h3>${member.firstName} ${member.lastName}</h3>
            <p class="col-sm-offset-1">status: ${order.orderStatus}</p>
            <hr/>
            <ul>
                <li>Member Id: ${member.memberId}</li>
                <li>Email: ${member.email}</li>
                <li>Phone: ${member.phone}</li>
                <li class="list-unstyled row divider-line"><hr class="col-lg-5 col-md-6 col-sm-6 col-xs-7"/></li>

            </ul>
            <hr/>
            <a class="btn btn-primary center-block" href="${pageContext.request.contextPath}/admin/order/">Update Order</a>
        </td>
        <c:if test="${loopStatus.index % 2 == 1 || loopStatus.last}">
            </tr>
        </c:if>
    </c:forEach>
</table>