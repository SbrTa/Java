<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control","no-cache, no-store, must-validate");
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		System.out.println("login jsp;;;");

		if(session.getAttribute("user")!=null){
			response.sendRedirect("login");
		}
	%>

	<form action="login" method="post">
		<br>User Id : <input type="text" placeholder="SbrTa" name="userName"/>
		<br>Password : <input type="text" placeholder="12345" name="password"/>
		<br><input type="submit" value="Login"/>
	</form>
</body>
</html>