<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="text-center">
    <h2>User page</h2>
    <p class="h4">for ${user.firstName} ${user.lastName}</p>
    <p class="h4">Recent Orders</p>
</div>
<table class="center-block table table-striped" id="list">
    <c:forEach var="order" items="${orders}" varStatus="loopStatus">
        <c:if test="${loopStatus.index % 2 == 0}">
            <tr>
        </c:if>

        <td>
            <p class="col-sm-offset-1 row h4"><span class="col-sm-6">${order.type} Order Status: ${order.orderStatus}</span></p>
            <hr/>
            <p class="col-sm-offset-2 row h5">${order.timeSinceLastUpdate()}</p>
            <ul>
                <li class="list-unstyled row divider-line"><hr/></li>
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
            <p>Notes: ${order.notes}</p>
        </td>
        <c:if test="${loopStatus.index % 2 == 1 || loopStatus.last}">
            </tr>
        </c:if>
    </c:forEach>
</table>
