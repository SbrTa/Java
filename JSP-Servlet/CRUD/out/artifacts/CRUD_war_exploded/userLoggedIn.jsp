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
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		if(session.getAttribute("user")==null){
		    response.sendRedirect("login.jsp");
		}
	%>


	<h1>Login successfull...</h1>
	

	<%
		User user = (User) session.getAttribute("user");
		String userName = null;
		try{
		    user.getUserName();
		}catch (Exception e){
		    e.printStackTrace();
		}
	%>


	<form action="logout">
		Hello <%=userName %> <input type="submit" value="Log Out"/>
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

	<table cellspacing="20">
		<col width="50">
		<col width="280">
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
			List<Story> book = null;
			try{
				book = (List<Story>) session.getAttribute("userBook");
			}catch (Exception e){
			    e.printStackTrace();
			}
			System.out.println(book.size());
			for(Story s : book){
			    String sName=null;
			    String sDate=null;
			    String sStory=null;
			    try{
			        sName=s.getUserName();
			        sDate=s.getDate();
			        sStory=s.getStory();
				}catch(Exception e) {
					e.printStackTrace();
				}
		%>

		<tr>
			<td><%=sName%></td>
			<td><%=sDate%></td>
			<td><%=sStory%></td>
		</tr>
		<tr>
			<br>
		</tr>
		<%}%>
		</tbody>
	</table>


</body>
</html>