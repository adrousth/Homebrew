<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar col-lg-3 col-md-3 col-sm-3 navbar-default" role="navigation">
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li><a href="/">Home</a></li>
            <li><a href="/about">About</a></li>
            <c:choose>
                <c:when test="${user == null}">
                    <li><a href="/login">Log in</a></li>
                </c:when>
                <c:otherwise>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown">Account <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/member">Profile</a></li>
                            <li><a href="#">Settings</a></li>
                            <li><a href="#">Orders</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout">Log out</a></li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
        <form class="navbar-form navbar-left">
            <div class="input-group">
                <input class="form-control" type="text" placeholder="Search">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">Submit</button>
                </span>
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Link</a></li>
        </ul>
    </div>
</div>