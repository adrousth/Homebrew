<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center-block" id="form">
    <form class="form-horizontal" action="/orderForm" method="POST">
        <h3 class="text-center">Order Form</h3>
        <p>plz enter quantities to the nearest 1/2 ounce</p>
        <p>total must be less than or equal to 10 ounces</p>
        <hr/>
        <fieldset>
            <c:forEach var="number" begin="1" end="5" step="1">
                <div class="form-group">
                    <label for="hop${number}" class="col-sm-3 control-label">Hop #${number}</label>
                    <div class="col-sm-6">

                        <select class="form-control" id="hop${number}" name="hop${number}">
                            <option value=""></option>
                            <c:forEach var="hop" items="${hops}">
                                <option value="${hop.assetId}">${hop.name} ~ qty in stock ${hop.currentStock}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-3 input-group">
                        <input class="form-control" id="hop${number}Qty" value="0.0" step="0.5" type="number" name="hop${number}Qty" min="0.0" max="10.0">
                        <div class="input-group-addon">oz</div>
                    </div>
                </div>
            </c:forEach>
            <div class="form-group">
                <label class="col-sm-3 col-sm-offset-6 control-label">Total</label>
                <div class="col-sm-3 input-group">
                    <input disabled="disabled" class="form-control" id="totalQty" type="text" value="0.0"/>
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