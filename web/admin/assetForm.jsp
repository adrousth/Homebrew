<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="center-block" id="form">
    <form class="form-horizontal" action="/admin/${type}/${asset.assetId}" method="POST">
        <c:choose>
            <c:when test="${type == \"grain\"}">
                <c:set value="lbs" var="unit"/>
            </c:when>
            <c:when test="${type == \"hop\"}">
                <c:set value="oz" var="unit"/>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${asset != null}">
                <h3 class="text-center">Update ${asset.name} ${type} form</h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center">New ${type} form</h3>
            </c:otherwise>
        </c:choose>

        <hr/>
        <fieldset>
            <c:if test="${asset != null}">
                <p class="help-block text-center">Update ${asset.name}, or add a new one <a href="${pageContext.request.contextPath}/admin/${type}">Here</a>.</p>
            </c:if>
            <div class="form-group">
                <label for="assetName" class="col-sm-3 control-label">Name</label>
                <div class="col-sm-9">
                    <input class="form-control" id="assetName" name="assetName" type="text" value="${asset.name}" placeholder="Name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="currentStock" class="col-sm-3 control-label">Current stock</label>
                <div class="col-sm-4">
                    <div class=" input-group">
                        <input class="form-control" id="currentStock" value="${asset.currentStock}" step="0.5" type="number" name="currentStock" placeholder="0" min="0"/>
                        <div class="input-group-addon">${unit}</div>
                    </div>
                </div>
            </div>
        </fieldset>
        <div class="form-group">
            <label for="description" class="col-sm-3 control-label">Description</label>
            <div class="col-sm-9">
                <textarea class="form-control" id="description" name="description" rows="3" placeholder="Description">${asset.description}</textarea>
            </div>
        </div>
        <input type="hidden" value="${asset.assetId}" name="id"/>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-8">
                <button type="submit" class="btn btn-primary btn-block">${button}</button>
            </div>
        </div>
    </form>
</div>