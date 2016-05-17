<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 4/21/2016
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <form class="form-horizontal col-lg-4 col-lg-offset-2" action="j_security_check" method="POST">
        <fieldset>
            <legend>Sign In</legend>
            <div class="form-group">
                <label for="inputUserId" class="col-lg-2 control-label">User Name (email)</label>
                <div class="col-lg-10">
                    <input class="form-control" id="inputUserId" placeholder="User ID" type="text" name="j_username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                <div class="col-lg-10">
                    <input class="form-control" id="inputPassword" placeholder="Password" type="password" name="j_password">
                </div>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </fieldset>
    </form>
</div>
</body>
</html>
