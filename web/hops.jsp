<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
    <c:forEach var="hop" items="${hops}">
        <li>
            <ul>
                <li>${hop.name}</li>
                <li>${hop.currentStock}</li>
                <li>${hop.description}</li>
            </ul>
        </li>
    </c:forEach>
</ul>