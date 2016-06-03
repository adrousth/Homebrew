<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center-block" id="form">
    <form class="form-horizontal" action="/orderForm" method="POST">
        <h3 class="text-center">Hop Order Form</h3>
        <ul>
            <li>Quantities must be to the nearest 1/2 ounce</li>
            <li>Total must be less than or equal to 10 ounces</li>
        </ul>
        <hr/>
        <fieldset>
            <c:forEach var="number" begin="1" end="5" step="1">
                <div class="form-group">
                    <label for="hop${number}" class="col-sm-3 control-label">Hop #${number}</label>
                    <div class="col-sm-6">

                        <select class="form-control" id="hop${number}" name="hop${number}">
                            <option value="">SELECTION #${number}</option>
                            <c:forEach var="hop" items="${hops}">
                                <option value="${hop.assetId}"
                                <c:if test="${results.orderItems[number - 1].assetId == hop.assetId}">selected="selected"</c:if>
                                >
                                    ${hop.name} --
                                    <c:choose>
                                        <c:when test="${hop.currentStock == 0}">
                                            out of stock
                                        </c:when>
                                        <c:otherwise>
                                            in stock ${hop.currentStock}oz
                                        </c:otherwise>
                                    </c:choose>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-3 input-group">
                        <c:set var="itemQuantity" value="0.0"/>
                        <c:if test="${not empty results.orderItems[number - 1]}">
                            <c:set var="itemQuantity" value="${results.orderItems[number - 1].quantity}"/>
                        </c:if>
                        <input class="form-control qty" id="hop${number}Qty" value="${itemQuantity}" step="0.5" type="number" name="hop${number}Qty" min="0.0" max="10.0">
                        <div class="input-group-addon">oz</div>
                    </div>
                </div>
            </c:forEach>
            <div class="form-group">
                <label class="col-sm-3 col-sm-offset-6 control-label">Total</label>
                <div class="col-sm-3 input-group">
                    <div class="form-control" id="totalQty" data-toggle="popover">0</div>
                    <input type="hidden" id="hiddenQty">
                    <div class="input-group-addon">oz</div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-8">
                    <button type="submit" class="btn btn-primary btn-block">Place order</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>