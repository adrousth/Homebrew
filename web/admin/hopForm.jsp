<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center-block" id="form">
    <form class="form-horizontal" action="/admin/hop/${hop.assetId}" method="POST">
        <c:choose>
            <c:when test="${hop != null}">
                <h3 class="text-center">Update Hop</h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center">New Hop Form</h3>
            </c:otherwise>
        </c:choose>

        <hr/>
        <fieldset>
            <c:if test="${hop != null}">
                <p class="help-block text-center">Update Hop, or add a new one <a href="${pageContext.request.contextPath}/admin/hop">Here</a>.</p>
            </c:if>
            <div class="form-group">
                <label for="hopName" class="col-sm-3 control-label">Name</label>
                <div class="col-sm-9">
                    <input class="form-control" id="hopName" name="hopName" type="text" value="${hop.name}" placeholder="Name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="currentStock" class="col-sm-3 control-label">Current stock</label>
                <div class="col-sm-4">
                    <div class=" input-group">
                        <input class="form-control" id="currentStock" value="${hop.currentStock}" step="0.5" type="number" name="currentStock" placeholder="0" min="0"/>
                        <div class="input-group-addon">oz</div>
                    </div>
                </div>
            </div>
        </fieldset>
        <div class="form-group">
            <label for="description" class="col-sm-3 control-label">Description</label>
            <div class="col-sm-9">
                <textarea class="form-control" id="description" name="description" rows="3" placeholder="Description">${hop.description}</textarea>
            </div>
        </div>
        <input type="hidden" value="${hop.assetId}" name="id"/>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-8">
                <button type="submit" class="btn btn-primary btn-block">${button}</button>
            </div>
        </div>
    </form>
</div>