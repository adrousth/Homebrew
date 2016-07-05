<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="center-block table table-striped" id="list">
    <h2 class="text-center text-capitalize">${member.firstName} ${member.lastName} ${orderStatus} Orders</h2>
    <p class="text-center">${orders.size()} results.</p>
    <c:forEach var="order" items="${orders}" varStatus="loopStatus">
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
        <h3>${order.type} order</h3>
        <p class="col-sm-offset-1">Order status: ${order.orderStatus}</p>
        <p class="col-sm-offset-1">${order.timeSinceLastUpdate()}</p>
        <hr/>
        <ul>
            <c:set var="total" value="${0.0}"/>
                <c:forEach var="item" items="${order.orderItems}">
                    <li class="row"><span class="col-lg-4 col-md-5 col-sm-5 col-xs-6">${item.asset.name}</span>${item.quantity} oz</li>
                    <c:set var="total" value="${total + item.quantity}"/>
                </c:forEach>
                <li class="list-unstyled row divider-line"><hr class="col-lg-5 col-md-6 col-sm-6 col-xs-7"/></li>
                <li class="list-unstyled row">
                    <span class="col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 col-lg-3 col-md-4 col-sm-4 col-xs-5">
                        Total:
                    </span>${total} oz
                </li>

            </ul>
            <hr/>
            <div class="h4">Notes: </div>
            <p>${order.notes}</p>
            <a class="btn btn-primary center-block" href="${pageContext.request.contextPath}/admin/order/${order.orderId}">Update Order</a>
        </div>
        <c:if test="${loopStatus.index % 2 == 1 || loopStatus.last}">
            </div>
        </c:if>
    </c:forEach>
</div>