
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.roy.spring.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div style="margin-bottom: 70px">
    <%
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        request.setAttribute("userId",userId);
    %>

    <div>
        <div>
            <h2>Welcome ${userDetails.name}</h2>
            <br/>
            <div>
                <form class="row" action="${pageContext.request.contextPath}/user/post/create" method="post">
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
            <h2 class="text-center text-primary">News feed </h2>
        </div>

        <div class="news-feed">
            <c:forEach var="req" items="${finalPost}">
                <div class="col-sm-12" style="padding-left: 0px">
                    <label class="col-sm-2" style="padding-left: 0px">${req.userName}</label>
                    <label class="col-sm-5" style="padding-left: 0px">${req.time}</label>
                </div>
                <div class="col-sm-12 beforeEditPost" style="padding-left: 0px">
                    <label class="col-sm-2" style="padding-left: 0px"></label><%--
                    <label class="col-sm-9" style="padding-left: 0px" >${req.content}</label>--%>
                    <p class="col-sm-9" style="padding-left: 0px" align="justify">${req.content}</p>
                </div>

                <c:set var="likerList" value='${likers.get(req.id)}' />
                <c:set var="dislikerList" value='${dislikers.get(req.id)}' />

                <div class="beforeEditPost col-sm-12" style="display: flex; margin-bottom: 20px">
                    <label class="col-sm-2" style="padding-left: 0px"></label>
                    <a href="${pageContext.request.contextPath}/user/post/like?postid=${req.id}">
                        <img src="${pageContext.request.contextPath}/resources/icon/like.png"
                             class="img-thumbnail" width="30px" height="30px"
                             <%--style="background-color: inherit; color: red; border:0px;"--%>
                            <c:if test="${likerList.indexOf(userId)<=0}">
                                 style="background-color: inherit; border:0px;"
                            </c:if>
                            <c:if test="${likerList.indexOf(userId)>0}">
                                style="background-color: #738aba; border:0px;"
                            </c:if>
                        >
                    </a>
                    <label style="padding-right: 10px">${likers.get(req.id).size()-1}</label>
                    <a href="${pageContext.request.contextPath}/user/post/dislike?postid=${req.id}">
                        <img src="${pageContext.request.contextPath}/resources/icon/dislike.png"
                             class="img-thumbnail" width="30px" height="30px"
                            <c:if test="${dislikerList.indexOf(userId)>0}">
                                 style="background-color: #738aba; border:0px;"
                            </c:if>
                            <c:if test="${dislikerList.indexOf(userId)<=0}">
                                 style="background-color: inherit; border:0px;"
                            </c:if>
                        >
                    </a>
                    <label style="padding-right: 10px">${dislikers.get(req.id).size()-1}</label>

                    <c:if test="${req.userName == sessionScope.user.getUserName()}">
                        <a href="${pageContext.request.contextPath}/user/post/edit?postid=${req.id}">
                            <img src="${pageContext.request.contextPath}/resources/icon/edit.png"
                                 class="img-thumbnail" width="30px" height="30px"
                                 style="background-color: inherit; border:0px;"
                            >
                        </a>
                        <label style="padding-right: 10px"></label>

                        <a href="${pageContext.request.contextPath}/user/post/delete?postid=${req.id}">
                            <img src="${pageContext.request.contextPath}/resources/icon/delete.png"
                                 class="img-thumbnail" width="30px" height="30px"
                                 style="background-color: inherit; border:0px;"
                            >
                        </a>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
    <br/>
    <br/>
    <p>.</p>
    <%--<div class="col-xs-6 col-sm-1"></div>--%>
</div>


