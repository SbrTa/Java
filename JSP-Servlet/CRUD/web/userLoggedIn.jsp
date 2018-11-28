<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="dto.*"
    %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Success</title>
</head>
<body>

	<%
		response.setHeader("Cache-Control","no-cache, no-store, must-validate");
		if(session.getAttribute("user")==null){
		    response.sendRedirect("login.jsp");
		}
	%>


	<h1>Login successfull...</h1>
	

	<%
		User user = (User) session.getAttribute("user");
	%>


	<form action="logout">
		Hello <%=user.getUserName() %> <input type="submit" value="Log Out"/>
	</form>

	<form action="postStory" method="post">
		<br><br>
		Post a Story <input type="text" name="story" size="100"/>
		<input type="submit" value="Post"/>
	</form>


	<br>
	<br>
	<br>
	<br>

	<table>
		<col width="50">
		<col width="300">
		<col width="400">
		<thead>
		<tr>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<%
			List<Story> book = (List<Story>) session.getAttribute("userBook");
			for(Story story : book){ %>
		<tr>
			<td><%=story.getUserName()%></td>
			<td><%=story.getDate()%></td>
			<td><%=story.getStory()%></td>
		</tr>
		<tr>
			<br>
		</tr>
		<%}%>
		</tbody>
	</table>


</body>
</html>