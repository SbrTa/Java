<%@ page import="dto.Story" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: BS-033
  Date: 11/28/2018
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<%
    response.setHeader("Cache-Control","no-cache, no-store, must-validate");
    if(session.getAttribute("user")==null){
        response.sendRedirect("login.jsp");
    }
%>
    <h1>Admin panel..</h1>

    <form action="logout">
        Hello Admin <input type="submit" value="Log Out"/>
    </form>


<h2> Request list</h2>

    <table>
        <thead>
        <tr>
            <th>SL.</th>
            <th>User</th>
            <th>Date</th>
            <th>Story</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <%
                List<Story> book = (List<Story>) session.getAttribute("book");
                int sl=1;
                for(Story story : book){ %>
                <tr>
                    <td><%=sl%></td>
                    <td><%=story.getUserName()%></td>
                    <td><%=story.getDate()%></td>
                    <td><%=story.getStory()%></td>
                    <td>
                        <form action="adminAction" method="post">
                            <input type="hidden" name="id" value="<%=story.getId()%>"/>
                            <%--<input type="hidden" name="action" value="accept"/>--%>
                            <input type="submit" value="accept" name="action"/>
                        </form>
                    </td>
                    <td>
                        <form action="adminAction" method="post">
                            <input type="hidden" name="id" value="<%=story.getId()%>"/>
                            <%--<input type="hidden" name="action" value="ignore"/>--%>
                            <input type="submit" value="ignore" name="action"/>
                        </form>
                    </td>
                </tr>
            <%
                sl++;
                }
            %>
        </tbody>
    </table>


</body>
</html>
