<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <label class="navbar-brand" >SpringMvcTiles</label>
        </div>
        <div id="navbar" class="collapse navbar-collapse from-right">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/gohome">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>

                <c:if test="${!empty sessionScope.user}" >
                    <li><a style="color: red;" href="${pageContext.request.contextPath}/logout">Log out</a></li>
                </c:if>

            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
