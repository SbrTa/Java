<%@ page import="com.roy.spring.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="col-sm-3" style="position: fixed; background-color: #007bff; padding: 15px; border-radius: 5%">
    <%
        User user = (User) session.getAttribute("user");
    %>
        <div >

            <div class="profile-pic text-center">
                <img src="${pageContext.request.contextPath}/resources/img/pro-pic.jpg" alt="" class="img-thumbnail" width="50%" height="50%"
                    style="
                            border-style: solid;
                            border-width: 5px;
                            border-color: darkgrey;
                            object-fit: fill;
                            border-radius: 50%;"
                >
            </div>

            <br/>

            <div id="viewInfo" style="padding-top: 10px; padding-bottom: 10px ">
                    <div class="col-sm-12">
                        <label class="col-sm-5">Name:</label>
                        <label class="col-sm-7">${userDetails.name}</label>
                    </div>

                    <div class="col-sm-12">
                        <label class="col-sm-5">Date of Birth:</label>
                        <label class="col-sm-7">${userDetails.dob}</label>
                    </div>

                    <div class="col-sm-12">
                        <label class="col-sm-5">Blood Group:</label>
                        <label class="col-sm-7">${userDetails.blood}</label>
                    </div>

                    <div class="col-sm-12">
                        <label class="col-sm-5">Current City:</label>
                        <label class="col-sm-7">${userDetails.city}</label>
                    </div>

                    <div class="col-sm-12">
                        <label class="col-sm-5">Email:</label>
                        <label class="col-sm-7"><%= user.getEmail() %></label>
                    </div>

                    <div class="col-sm-12">
                        <label class="col-sm-5">Contact:</label>
                        <label class="col-sm-7">${userDetails.contact}</label>

                    </div>

                    <div class="col-sm-12">
                        <label class="col-sm-5">Relationship Status:</label>
                        <label class="col-sm-7">${userDetails.relation}</label>
                    </div>

                    <div class="col-sm-12">
                        <%--<label class="col-sm-5">Bio</label>--%>
                        <blockquote class="blockquote text-center border-0">
                            <p>${userDetails.bio}</p>
                            <footer class="blockquote-footer">${userDetails.name}</footer>
                        </blockquote>
                    </div>
                    <div class="col-sm-12 ">
                        <label class="col-sm-3"></label>
                        <form action="${pageContext.request.contextPath}/edituserdetailspage">
                            <input id="editdetails" type="submit" class="btn btn-danger text-center" value="Edit Details"/>
                        </form>
                    </div>

            </div>


<%--
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
                        &lt;%&ndash;<input class="col-sm-4" type="text" name="relation"/>&ndash;%&gt;
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
                        &lt;%&ndash;<input class="col-sm-4" type="text" name="relation"/>&ndash;%&gt;
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
--%>

        </div>
    <%--
        <div class="col-xs-6 col-sm-1"></div>
    --%>
    <%--<div>
        <div>
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
        </div>

        <div class="news-feed" style="  /*background-image: url('/resources/img/bg5.png');*/
                                        background-color: #c4c3c0;
                                        background-size: cover;
                                        opacity: 0.5;
                                        background-repeat: no-repeat;
                                        padding: 10px;
                                        border-style: solid; border-radius: 1%; border-color: #c4c3c0;"">
        <c:forEach var="req" items="${finalPost}">
            <div class="col-sm-12" style="padding-left: 0px">
                <label class="col-sm-2" style="padding-left: 0px">${req.userName}</label>
                <label class="col-sm-5" style="padding-left: 0px">${req.time}</label>
            </div>
            <div class="col-sm-12 beforeEditPost" style="padding-left: 0px">
                <label class="col-sm-2" style="padding-left: 0px"></label>
                <label class="col-sm-9" style="padding-left: 0px">${req.content}</label>
            </div>
            &lt;%&ndash;<div class="col-sm-12" style="padding-left: 0px">
                <label class="col-sm-2" style="padding-left: 0px"></label>
                <label class="">0</label>
                <label class="">like</label>
                <label class="">unlike</label>
                <label class="">edit</label>
                <label class="">delete</label>
            </div>&ndash;%&gt;
            <div class="beforeEditPost col-sm-12" style="display: flex; margin-bottom: 20px">
                <label class="col-sm-2" style="padding-left: 0px"></label>
                <a href="${pageContext.request.contextPath}/likepost?postid=${req.id}">
                    <img src="${pageContext.request.contextPath}/resources/icon/like.png"
                         class="img-thumbnail" width="30px" height="30px"
                         style="background-color: inherit; border:0px;"
                    >
                </a>
                <label style="padding-right: 10px">${likers.get(req.id).size()-1}</label>
                <a href="${pageContext.request.contextPath}/dislikepost?postid=${req.id}">
                    <img src="${pageContext.request.contextPath}/resources/icon/dislike.png"
                         class="img-thumbnail" width="30px" height="30px"
                         style="background-color: inherit; border:0px;"
                    >
                </a>
                <label style="padding-right: 10px">${dislikers.get(req.id).size()-1}</label>

                <a href="${pageContext.request.contextPath}/editpost?postid=${req.id}">
                    <img src="${pageContext.request.contextPath}/resources/icon/edit.png"
                         class="img-thumbnail" width="30px" height="30px"
                         style="background-color: inherit; border:0px;"
                    >
                </a>
                <label style="padding-right: 10px"></label>

                <a href="${pageContext.request.contextPath}/deletepost?postid=${req.id}">
                    <img src="${pageContext.request.contextPath}/resources/icon/delete.png"
                         class="img-thumbnail" width="30px" height="30px"
                         style="background-color: inherit; border:0px;"
                    >
                </a>
            </div>

            &lt;%&ndash;<div>
                <label>id = ${req.id}</label>
                <label>like = ${likers.get(req.id)}</label>
                <label>liker = ${likers.get(req.id).size()-1}</label>

                <label>disliker = ${dislikers.get(req.id).size()-1}</label>
            </div>&ndash;%&gt;

        </c:forEach>
    </div>--%>
</div>


