<%--
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

    <title>Admin</title>
</head>
<body id="body-img">


<div class="row">
    <div class="col-xs-6 col-sm-2"></div>
    <div class="col-xs-6 col-sm-8">
        <h2>Admin panel......</h2>
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
            <tr>
                <td>0</td>
                <td>time</td>
                <td>name</td>
                <td>email</td>
                <td>content kuyfg gdfsdhjj fcas jkdkgf asdkjk gfdsvsd af dtfhgfh.</td>
                <td><button>Accept</button></td>
                <td><button>Ignore</button></td>
            </tr>
            <% int i=1; %>
            <c:forEach var="req" items="${notices}">
                <tr>
                    <td><%=i%></td>
                    <td>${req.time}</td>
                    <td>${req.name}</td>
                    <td>${req.email}</td>
                    <td>${req.content}</td>
                    <td><button>Accept</button></td>
                    <td><button>Ignore</button></td>
                </tr>
                <%i++;%>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="col-xs-6 col-sm-2"></div>
</div>


</body>
</html>
