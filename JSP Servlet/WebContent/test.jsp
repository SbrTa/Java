<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP test</title>
</head>
<body>
	<h1>Testing JSP</h1>
	<%
	int i=1;
	int j=2;
	int k = i+j;
	out.println("value of  k = "+k);
	%>
	value of k = <%=k %>
	
	<br>
	Time : <%=new Date() %>
</body>
</html>