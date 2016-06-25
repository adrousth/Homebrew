<form class="form-horizontal center-block searchForm" method="POST" action="${pageContext.request.contextPath}/admin/newMember">
    <fieldset>
        <legend class="h4 col-sm-12">Name</legend>
        <div class="form-group">
            <label for="firstName" class="col-sm-3 control-label">First</label>
            <div class="col-sm-6">
                <input class="form-control" type="text" id="firstName" name="firstName"/>
            </div>
        </div>
        <div class="form-group">
            <label for="lastName" class="col-sm-3 control-label">Last</label>
            <div class="col-sm-6">
                <input class="form-control" type="text" id="lastName" name="lastName"

                />
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend class="h4 col-sm-12">Email</legend>
        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Email address</label>
            <div class="col-sm-6">
                <input class="form-control" type="email" id="email" name="email"/>
            </div>
        </div>
        <fieldset>
            <legend class="h4 col-sm-12">Phone</legend>
            <div class="form-group">
                <label for="home" class="col-sm-3 control-label">Home</label>
                <div class="col-sm-6">
                    <input class="form-control" type="tel" id="home" name="home"/>
                </div>
            </div>
            <div class="form-group">
                <label for="cell" class="col-sm-3 control-label">Cell</label>
                <div class="col-sm-6">
                    <input class="form-control" type="tel" id="cell" name="cell"/>
                </div>
            </div>
        </fieldset>
    </fieldset>

    <div class="form-group">
        <div class="col-sm-6 col-sm-offset-3">
            <button type="submit" class="btn btn-block btn-primary">Submit</button>
        </div>
    </div>
</form>
