<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center-block table table-striped" id="list">
    <h2 class="text-center">Members</h2>
    <p class="text-center">${orders.size()} results.</p>
    <c:forEach var="member" items="${members}" varStatus="loopStatus">
    <c:if test="${loopStatus.index % 2 == 0}">
        <c:choose>
            <c:when test="${loopStatus.index % 4 == 0}">
                <div class="row odd list-row">
            </c:when>
            <c:otherwise>
                <div class="row even list-row">
            </c:otherwise>
        </c:choose>
    </c:if>
        <div class="col-md-6 list-item">
            <h3>${member.firstName} ${member.lastName}</h3>
            <hr/>
            <ul>
                <li class="row"><span class="col-lg-4 col-md-5 col-sm-4 col-xs-2">Member-Id:</span>${member.memberId}</li>

                <li class="row"><span class="col-lg-4 col-md-5 col-sm-4 col-xs-2">Email:</span>${member.email}</li>
                <li class="row"><span class="col-lg-4 col-md-5 col-sm-4 col-xs-2">Phone:</span>${member.phone}</li>

            </ul>
            <hr/>
            <a class="btn btn-primary center-block" href="${pageContext.request.contextPath}/admin/member/orders/${member.memberId}">View ${member.firstName}'s orders</a>
        </div>
        <c:if test="${loopStatus.index % 2 == 1 || loopStatus.last}">
            </div>
        </c:if>
    </c:forEach>
</div>