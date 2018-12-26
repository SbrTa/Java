<%--
  Created by IntelliJ IDEA.
  User: BS-033
  Date: 12/3/2018
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/background.css" rel="stylesheet" type="text/css">

    <title>Notices</title>
</head>
<body id="body-img">


<div class="row">
    <div class="col-xs-6 col-sm-2"></div>
    <div class="col-xs-6 col-sm-8">
        <h2>Notices......</h2>
        <br/>
        <br/>
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <th>id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Message</th>
            </thead>
            <tbody>
            <% int i=1; %>
            <c:forEach var="notice" items="${notices}">
                <tr>
                    <td><%=i%></td>
                    <td>${notice.name}</td>
                    <td>${notice.email}</td>
                    <td>${notice.text}</td>
                </tr>
                <%i++;%>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="clearfix visible-xs-block"></div>
    <div class="col-xs-6 col-sm-2"></div>
</div>


</body>
</html>
