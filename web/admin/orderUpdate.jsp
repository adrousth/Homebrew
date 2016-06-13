<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-part" class="center-block">
    <h3 class="col-sm-offset-1">${order.member.firstName} ${order.member.lastName}</h3>
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
    <div class="h4">Notes: </div>
    <p>${order.notes}</p>
    <form method="post" action="${pageContext.request.contextPath}/admin/order/${order.orderId}">
        <div class="form-group">
            <label for="status" class="col-sm-3 control-label">Order Status</label>
            <div class="col-sm-6">
                <select class="form-control" id="status" name="status">
                    <option value="unfilled">unfilled</option>
                    <option value="filled">Filled</option>
                    <option value="complete">Complete</option>
                </select>
            </div>
        </div>
        <input type="hidden" value="${order.orderId}" name="orderId">
        <button class="btn btn-primary" type="submit">Update</button>
    </form>
</div>