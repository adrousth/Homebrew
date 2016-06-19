<h3 class="text-center">Search</h3>
<form class="form-horizontal center-block searchForm" method="get" action="${pageContext.request.contextPath}/search/members">
    <fieldset class="">
        <legend class="h4 col-sm-12">Members</legend>

        <div class="form-group">
            <label for="firstName" class="col-sm-3 control-label">First name</label>
            <div class="col-sm-6">
                <input class="form-control" type="text" name="firstName" id="firstName"/>
            </div>
        </div>


        <div class="form-group">
            <label for="lastName" class="col-sm-3 control-label">Last name</label>
            <div class="col-sm-6">
                <input class="form-control" type="text" name="lastName" id="lastName"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
                <button type="submit" class="col-sm-5 btn btn-primary">Search</button>
            </div>
        </div>

    </fieldset>
</form>

<form class="form-horizontal center-block searchForm" method="get" action="${pageContext.request.contextPath}/search/orders">
    <fieldset>
        <legend class="h4 col-sm-12">Order</legend>

        <div class="form-group">
            <label for="orderStatus" class="col-sm-3 control-label">Status</label>
            <div class="col-sm-5">
                <select class="form-control" id="orderStatus" name="orderStatus">
                    <option value="">All</option>
                    <option value="unfilled">Unfilled</option>
                    <option value="filled">Filled</option>
                    <option value="complete">Complete</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3" style="">Type</label>
            <div class="col-sm-6">
                <label class="radio-inline">
                    <input type="radio" name="type" value="HOP"> Hop
                </label>
                <label class="radio-inline">
                    <input type="radio" name="type" value="GRAIN"> Grain
                </label>
                <label class="radio-inline">
                    <input type="radio" name="type" value="BOTH" checked="checked"> Both
                </label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
                <button type="submit" class="col-sm-5 btn btn-primary">Search</button>
            </div>
        </div>
    </fieldset>

</form>