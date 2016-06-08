<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar col-lg-3 col-md-3 col-sm-3 navbar-default" role="navigation">
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
            <c:choose>
                <c:when test="${user == null}">
                    <li><a href="${pageContext.request.contextPath}/login">Log in</a></li>
                </c:when>
                <c:otherwise>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown">Account <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/member">Profile</a></li>
                            <li><a href="#">Settings</a></li>
                            <li><a>Order</a></li>
                            <li><a href="${pageContext.request.contextPath}/member/orderForm?form=hops"> - Hops</a></li>
                            <li><a href="${pageContext.request.contextPath}/member/orderForm?form=grains"> - Grains</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/orders/hop">Hop Orders</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/logout">Log out</a></li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
            <li class="dropdown">
                <a class="dropdown-toggle" href="#" data-toggle="dropdown">Members <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Beer School</a></li>
                    <li><a href="#">Club Directory</a></li>
                    <li><a href="#">Member Benefits</a></li>
                    <li><a href="${pageContext.request.contextPath}/member/hops"> - Hops</a></li>
                    <li><a href="${pageContext.request.contextPath}/member/grains"> - Grains</a></li>
                    <li><a href="#">Photo Gallery</a></li>
                </ul>
            </li>
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
            <li><a href="#">Calendar</a></li>
            <li><a href="#">Contact us</a></li>
            <li><a href="#">Great Taste of the Midwest</a></li>
        </ul>
    </div>
</div>