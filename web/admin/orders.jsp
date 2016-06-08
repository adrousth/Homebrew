<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="center-block table table-striped" id="list">

    <h2 class="text-center">Hop Orders</h2>
    <c:forEach var="order" items="${orders}" varStatus="loopStatus">
        <c:if test="${loopStatus.index % 2 == 0}">
            <tr>
        </c:if>
        <td>
            <h3>${order.member.firstName} ${order.member.lastName}</h3>
            <p class="col-sm-offset-1">status: ${order.orderStatus}</p>
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
            <p>Notes: ${order.notes}</p>
        </td>
        <c:if test="${loopStatus.index % 2 == 1 || loopStatus.last}">
            </tr>
        </c:if>
    </c:forEach>
</table>