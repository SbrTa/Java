<%@ page import="com.test.dto.User" %>
<%@ page import="com.test.dto.UserDetails" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <%
        User user = (User) session.getAttribute("user");
    %>
    <div>
        <div>
            <h2>Welcome ${userDetails.name}</h2>

            <br/>
        </div>

        <div class="news-feed">

                <div class="col-sm-12" style="padding-left: 0px">
                    <label class="col-sm-2" style="padding-left: 0px">${userPost.userName}</label>
                    <label class="col-sm-5" style="padding-left: 0px">${userPost.time}</label>
                </div>
                <div class="col-sm-12 beforeEditPost padding-0">
                    <label class="col-sm-2" style="padding-left: 0px"></label>
                    <div class="padding-0 col-sm-8">
                        <form  action="${pageContext.request.contextPath}/saveEditedPost" method="post">
                            <!-- Textarea -->
                            <div class="form-group">
                                <textarea rows="4" cols="70" class="form-control" id="content" name="content" >${userPost.content}</textarea>
                                <input type="hidden" name="postid" value="${userPost.id}"/>
                            </div>

                            <!-- Button -->
                            <div class="form-group text-center">
                                <label class="col-sm-5"></label>
                                <input class="col-sm-2 btn btn-primary text-center" type="submit" value="Save"/>
                            </div>
                        </form>
                    </div>
                </div>
                <br/>
        </div>
    </div>
</div>

