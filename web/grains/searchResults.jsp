<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</ul>
<c:forEach var="grain"  items="${results}">

    <div class="container panel panel-primary col-lg-7">
        <div class="panel-heading">
            <h3 class="panel-title">${book.title}</h3>
        </div>
        <div class="panel-body">
            <ul class="container col-lg-3">
                <li>grain id: ${book.isbn}</li>
                <li>Publisher: ${book.publisher}</li>
                <li>Edition: ${book.edition}</li>
                <li>Copies: ${book.copies}</li>
                <li>Copies Available: ${book.availableCopies}</li>
                <li>Number of Pages: ${book.numberPages}</li>
                <li>Format: ${book.format}</li>
            </ul>
            <div class="container col-lg-8">

                    ${book.description}

            </div>
        </div>
    </div>

</c:forEach>