<%--
  Created by IntelliJ IDEA.
  User: BS-033
  Date: 11/26/2018
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Complete Registration</h2>

    <form action="register" method="post">
        <br>User Name : <input type="text" name="userName"/>
        <br>Password : <input type="text" name="password"/>
        <br>Register : <input type="submit"/>
    </form>

    <form action="login.jsp">
        <br> Login :  <input type="submit"/>
    </form>
</body>
</html>
