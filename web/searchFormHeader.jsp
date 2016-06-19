<form class="form-horizontal center-block" style="width: 550px;" action="" method="POST">
    <h3 class="text-center">Search</h3>
    <fieldset class="row">
        <legend class="h4 col-sm-12">Name</legend>
        <div class="col-sm-6">
            <div class="form-group">
                <label for="firstName" class="col-sm-4 control-label">First</label>
                <div class="col-sm-8">
                    <input class="form-control" type="text" id="firstName"/>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="form-group">
                <label for="lastName" class="col-sm-2 control-label">Last</label>
                <div class="col-sm-9">
                    <input class="form-control" type="text" id="lastName"/>
                </div>
            </div>
        </div>
    </fieldset>
    <fieldset class="row">
        <legend class="h4 col-sm-12">Order</legend>
        <div class="col-sm-6">
            <div class="form-group">
                <label for="orderStatus" class="col-sm-4 control-label">Status</label>
                <div class="col-sm-8">
                    <select class="form-control" id="orderStatus" name="orderStatus">
                        <option value="all">All</option>
                        <option value="unfilled">Unfilled</option>
                        <option value="filled">Filled</option>
                        <option value="complete">Complete</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="">
                <label class="checkbox-inline">
                    <input type="checkbox" name="orderType" value="hop"> Hop
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="orderType" value="grain"> Grain
                </label>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </fieldset>
</form>