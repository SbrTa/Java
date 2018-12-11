<%--
  Created by IntelliJ IDEA.
  User: BS-033
  Date: 12/5/2018
  Time: 12:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/background.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/js/bootstrap.js" rel="stylesheet" type="text/css">
    <%--<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
</head>
<body id="body-img">
<div class="container">
    <br/>
    <br/>
    <br/>

    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h1>Login</h1>
            </div>
            <br/>
            <br/>
            <br/>
            <form id="Login">
                <div class="form-group">
                    <input type="email" class="form-control" id="inputEmail" placeholder="User Name">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
                <br/>
                <div class="reset">
                    <a href="">Register</a>
                </div>
            </form>
        </div>
    </div></div></div>


</body>
</html>
