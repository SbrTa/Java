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
                        <form action="${pageContext.request.contextPath}/user/details/edit">
                            <input id="editdetails" type="submit" class="btn btn-danger text-center" value="Edit Details"/>
                        </form>
                    </div>
            </div>

        </div>
</div>


