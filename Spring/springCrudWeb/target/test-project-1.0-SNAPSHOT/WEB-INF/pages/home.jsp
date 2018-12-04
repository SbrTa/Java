<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%--
  Created by IntelliJ IDEA.
  User: nasir
  Date: 12/22/16
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">
    <head>
        <title>Test Project APP</title>
    </head>
    <body class="full-page-background">
        <h1>Home</h1>

        <p><a href="${pageContext.request.contextPath}/notices">View all notice</a></p>
        <p><a href="${pageContext.request.contextPath}/createnotice">Create notice</a></p>
    </body>
</html>
