
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="center-block" id="form">
    <form class="form-horizontal" action="/login" method="POST">
        <h3 class="text-center">Sign In</h3>
        <hr/>
        <fieldset>
            <div class="form-group">
                <label for="inputUserId" class="col-sm-3 control-label">User name</label>
                <div class="col-sm-8">
                    <input class="form-control" id="inputUserId" placeholder="User name" type="text" name="username" autofocus="autofocus">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-3 control-label">Password</label>
                <div class="col-sm-8">
                    <input class="form-control" id="inputPassword" placeholder="Password" type="password" name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-8">
                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>