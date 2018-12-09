<%@ page import="com.test.dto.User" %>
<%@ page import="com.test.dto.UserDetails" %><%--
  Created by IntelliJ IDEA.
  User: BS-033
  Date: 12/5/2018
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/css/background.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/user.js"></script>

    <title>User</title>

</head>
<body id="body-img">


<div class="row">
    <%
        User user = (User) session.getAttribute("user");
    %>
    <div class="col-xs-6 col-sm-3">

        <div class="profile-pic">
            <img src="${pageContext.request.contextPath}/resources/img/pro-pic.jpg" alt="" class="img-thumbnail" width="50%" height="50%"
                style="
                        margin-left: 10%;
                        border-style: solid;
                        border-width: 5px;
                        border-color: darkgrey;
                        object-fit: fill;
                        border-radius: 50%;"
            >
        </div>

        <br/>

        <div id="viewInfo" style="border: thin solid floralwhite; border-radius: 5%; padding-top: 10px; padding-bottom: 10px ">
                <div>
                    <label class="col-sm-4">Name:</label>
                    <label class="col-sm-7">${userDetails.name}</label>
                </div>
                <div>
                    <label class="col-sm-4">Date of Birth:</label>
                    <label class="col-sm-7">${userDetails.dob}</label>
                </div>
                <div>
                    <label class="col-sm-4">Blood Group:</label>
                    <label class="col-sm-7">${userDetails.blood}</label>
                </div>
                <div>
                    <label class="col-sm-4">Current City:</label>
                    <label class="col-sm-7">${userDetails.city}</label>
                </div>
                <div>
                    <label class="col-sm-4">Email:</label>
                    <label class="col-sm-7"><%= user.getEmail() %></label>
                </div>
                <div>
                    <label class="col-sm-4">Contact:</label>
                    <label class="col-sm-7">${userDetails.contact}</label>

                </div>
                <div>
                    <label class="col-sm-4">Relationship Status:</label>
                    <label class="col-sm-7">${userDetails.relation}</label>
                </div>
                <br/>
                <div>
                    <%--<label class="col-sm-5">Bio</label>--%>
                    <blockquote class="blockquote text-center">
                        <p>${userDetails.bio}</p>
                        <footer class="blockquote-footer">${userDetails.name}</footer>
                    </blockquote>
                </div>
                <div>
                    <label class="col-sm-2"></label>
                    <input id="editdetails" class="btn btn-primary col-sm-6" value="Edit Details"/>
                </div>

        </div>


        <div id="editInfo" style="display: none; border: thin solid floralwhite; border-radius: 5%; padding-top: 10px; padding-bottom: 10px ">
            <form action="${pageContext.request.contextPath}/editUserDetails" method="post" onsubmit="return validUserDetails()">
                <div>
                    <label class="col-sm-7">Name</label>
                    <input class="col-sm-4" type="text" id="name" name="name" value="${userDetails.name}"/>
                </div>
                <div>
                    <label class="col-sm-7">Date of Birth</label>
                    <input class="col-sm-4" type="date" id="dob" name="dob"  value="${userDetails.dob}"/>
                </div>
                <div>
                    <label class="col-sm-7">Blood Group</label>
                    <input class="col-sm-4" type="text" id="blood" name="blood" value="${userDetails.blood}"/>
                </div>
                <div>
                    <label class="col-sm-7">Gender</label>
                    <%--<input class="col-sm-4" type="text" name="relation"/>--%>
                    <select name="gender" class="col-sm-4 custom-form-control" id="gender" >
                        <option value="">Choose</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Transverse">Transverse</option>
                    </select>
                </div>
                <div>
                    <label class="col-sm-7">Current City</label>
                    <input class="col-sm-4" type="text" id="city" name="city" value="${userDetails.city}"/>
                </div>
                <div>
                    <label class="col-sm-7">Contact</label>
                    <input class="col-sm-4" type="text" id="contact" name="contact" value="${userDetails.contact}"/>
                </div>
                <div>
                    <label class="col-sm-7">Relationship Status</label>
                    <%--<input class="col-sm-4" type="text" name="relation"/>--%>
                    <select name="relation" class="col-sm-4 custom-form-control" id="relation">
                        <option value="">Choose</option>
                        <option value="Single">Single</option>
                        <option value="Married">Married</option>
                        <option value="Engaged">Engaged</option>
                        <option value="Complicated">Complicated</option>
                    </select>
                </div>
                <div>
                    <label class="col-sm-3">Bio</label>
                    <textarea class="col-sm-8" id="bio" name="bio">${userDetails.bio}</textarea>
                </div>
                <div>
                    <label class="col-sm-2"></label>
                    <input class="col-sm-6" type="submit" class="btn btn-primary" value="save"/>
                </div>


            </form>
        </div>

    </div>
    <div class="col-xs-6 col-sm-1"></div>
    <div class="col-xs-6 col-sm-6">
        <h2>Welcome ${userDetails.name}</h2>
        <form action="${pageContext.request.contextPath}/logout">
            <button class="btn btn-danger" type="submit">Log Out</button>
        </form>
        <br/>
        <div>
        <form class="row" action="${pageContext.request.contextPath}/createPost" method="post">
            <!-- Textarea -->
            <div class="form-group col-sm-6">
                    <textarea class="form-control" id="content" name="content" placeholder="Whats on your mind !"></textarea>
            </div>

            <!-- Button -->
            <div class="form-group col-sm-2">
                <input class="btn-block btn btn-primary" type="submit" value="post"/>
            </div>
        </form>
        </div>


        <br/>
        <h4>News feed </h4>
<%--
        <table class="table table-hover table-borderless">
            <col width="150">
            <col width="250">
            <thead>
                <th></th>
                <th></th>
                <th></th>
            </thead>
            <tbody>
            <c:forEach var="req" items="${finalPost}">
                <tr>
                    <td>${req.userName}</td>
                    <td>${req.time}</td>
                    <td>${req.content}</td>
                    &lt;%&ndash;<td>
                        <form action="${pageContext.request.contextPath}/pendingAction" method="post">
                            <input type="hidden" name="id" value="${req.id}"/>
                            <input class="btn btn-success" type="submit" value="accept" name="action"/>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/pendingAction" method="post">
                            <input type="hidden" name="id" value="${req.id}"/>
                            <input class="btn btn-danger" type="submit" value="ignore" name="action"/>
                        </form>
                    </td>&ndash;%&gt;

                </tr>
            </c:forEach>

            </tbody>
        </table>
--%>

        <div class="news-feed">
            <c:forEach var="req" items="${finalPost}">
                <div class="col-sm-12" style="padding-left: 0px">
                    <label class="col-sm-2" style="padding-left: 0px">${req.userName}</label>
                    <label class="col-sm-5">${req.time}</label>
                </div>
                <div class="col-sm-12" style="padding-left: 0px">
                    <label class="col-sm-2" style="padding-left: 0px"></label>
                    <label class="col-sm-9">${req.content}</>
                </div>
                <div class="col-sm-12" style="padding-left: 0px">
                    <label class="col-sm-2" style="padding-left: 0px"></label>
                    <label class="">0</label>
                    <label class="">like</label>
                    <label class="">unlike</label>
                    <label class="">edit</label>
                    <label class="">delete</label>
                </div>
                <br/>
            </c:forEach>
        </div>
    </div>
    <div class="col-xs-6 col-sm-2"></div>
</div>


</body>
</html>
