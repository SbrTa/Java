<%@ page import="com.test.dto.User" %><%--
  Created by IntelliJ IDEA.
  User: BS-033
  Date: 12/5/2018
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/background.css" rel="stylesheet" type="text/css">

    <title>User</title>
</head>
<body id="body-img">


<div class="row">
    <%
        User user = (User) session.getAttribute("user");
    %>
    <div class="col-xs-6 col-sm-2"></div>
    <div class="col-xs-6 col-sm-8">
        <h2>Welcome <%=user.getName()%></h2>
        <form action="${pageContext.request.contextPath}/logout">
            <button class="btn btn-danger" type="submit">Log Out</button>
        </form>
        <br/>
        <div>
        <form class="row" action="${pageContext.request.contextPath}/createPost" method="post">
            <!-- Textarea -->
            <div class="form-group col-sm-6">
                    <textarea class="form-control" id="content" name="content" placeholder="Whats on your mind !"></textarea>
            </div>

            <!-- Button -->
            <div class="form-group col-sm-2">
                <input class="btn-block btn btn-primary" type="submit" value="post"/>
            </div>
        </form>
        </div>


        <br/>
        <h4>News feed </h4>
        <table class="table table-hover table-borderless">
            <col width="150">
            <col width="250">
            <thead>
                <th></th>
                <th></th>
                <th></th>
            </thead>
            <tbody>
            <c:forEach var="req" items="${finalPost}">
                <tr>
                    <td>${req.userName}</td>
                    <td>${req.time}</td>
                    <td>${req.content}</td>
                    <%--<td>
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
                    </td>--%>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="col-xs-6 col-sm-2"></div>
</div>


</body>
</html>
