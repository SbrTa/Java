
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<div class="row">

    <div>
        <h2>Admin panel......</h2>
        <%--<form action="${pageContext.request.contextPath}/logout">
            <button class="btn btn-danger" type="submit">Log Out</button>
        </form>--%>
        <br/>
        <br/>
        <h4>Pending Requests : </h4>
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <th>SL</th>
            <th>Time</th>
            <th>Name</th>
            <th>Email</th>
            <th>Content</th>
            </thead>
            <tbody>
            <% int i=1; %>
            <c:forEach var="req" items="${pending}">
                <tr>
                    <td><%=i%></td>
                    <td>${req.time}</td>
                    <td>${req.userName}</td>
                    <td>${req.email}</td>
                    <td>${req.content}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/pendingAction" method="post">
                            <input type="hidden" name="id" value="${req.id}"/>
                            <input class="btn btn-success" type="submit" value="accept" name="action"/>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/pendingAction" method="post">
                            <input type="hidden" name="id" value="${req.id}"/>
                            <input class="btn btn-danger" type="submit" value="ignore" name="action"/>
                        </form>
                    </td>

                </tr>
                <%i++;%>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

