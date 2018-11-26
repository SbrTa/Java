<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="dto.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
	<h1>Login successfull...</h1>
	
	
<%-- 	
	<%
	//User user = (User) session.getAttribute("user");
	User user = (User) request.getAttribute("user");
	%>
	Hello <%=user.getUserName() %>
	
 --%>
 
 	<!-- JSTL -->
	<jsp:useBean id="user" class="dto.User" scope="request">
		<jsp:setProperty property="userName" name="user" value="NewUser"/>
	</jsp:useBean> 	
	Hello <jsp:getProperty property="userName" name="user"/>


	<form action="userInfoServlet" method="post">
		<br> Name : <input type="text" name="name"/>
		<br> Age : <input type="text" name="age"/>
		<br> Nationality : <input type="text" name="nationality"/>
		<br> <input type="submit" placeholder="Save"/>
	</form>


</body>
</html>