
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="row">
    <div>
        <form class="form-horizontal form-signin" style="max-width: 600px" action="${pageContext.request.contextPath}/user/details/edit/done" method="post" onsubmit="return validUserDetails()">

            <fieldset>

                <div>
                    <h2 class="form-signin-heading">Edit user details</h2>
                </div>
                <br/>
                <br/>

                <div class="form-group row col-sm-12">
                    <label class="col-sm-7">Name</label>
                    <input class="col-sm-4" type="text" id="name" name="name" value="${userDetails.name}"/>
                </div>
                <div class="form-group row col-sm-12">
                    <label class="col-sm-7">Date of Birth</label>
                    <input class="col-sm-4" type="date" id="dob" name="dob"  value="${userDetails.dob}"/>
                </div>
                <div class="form-group row col-sm-12">
                    <label class="col-sm-7">Blood Group</label>
                    <input class="col-sm-4" type="text" id="blood" name="blood" value="${userDetails.blood}"/>
                </div>
                <div class="form-group row col-sm-12">
                    <label class="col-sm-7">Gender</label>
                    <%--<input class="col-sm-4" type="text" name="relation"/>--%>
                    <select name="gender" class="col-sm-4 custom-form-control" id="gender" >
                        <option value="">Choose</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Transverse">Transverse</option>
                    </select>
                </div>
                <div class="form-group row col-sm-12">
                    <label class="col-sm-7">Current City</label>
                    <input class="col-sm-4" type="text" id="city" name="city" value="${userDetails.city}"/>
                </div>
                <div class="form-group row col-sm-12">
                    <label class="col-sm-7">Contact</label>
                    <input class="col-sm-4" type="text" id="contact" name="contact" value="${userDetails.contact}"/>
                </div>
                <div class="form-group row col-sm-12">
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
                <div class="form-group row col-sm-12">
                    <label class="col-sm-3">Bio</label>
                    <textarea class="col-sm-8" id="bio" name="bio">${userDetails.bio}</textarea>
                </div>

                <%--Button--%>
                <div class="form-group row col-sm-12">
                    <div class="col-sm-6">
                        <label class="control-label" for="submit"></label>
                    </div>
                    <div class="col-sm-3">
                        <button id="submit" name="submit" class="btn btn-primary">save</button>
                    </div>
                </div>


            </fieldset>
        </form>
    </div>
</div>

