<%--
  Created by IntelliJ IDEA.
  User: BS-033
  Date: 12/3/2018
  Time: 8:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create Notice</title>
    <link href="${pageContext.request.contextPath}/resources/css/background.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
</head>
<body id="body-img">

<div class="row">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form:form class="form-horizontal" commandName="notice"
                   action="${pageContext.request.contextPath}/doCreate" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend>Create Notice</legend>
                <br/>
                <br/>

                <!-- Text input-->
                <div class="form-group row col-sm-12">
                    <div class="col-sm-3">
                        <label class="control-label" for="name">Name</label>
                    </div>
                    <div class="col-sm-7">
                        <form:input path="name" id="name" name="name" type="text" placeholder="John Smith" class="form-control input-md"/>
                        <form:errors path="name" cssClass="alert-danger"></form:errors>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group row col-sm-12">
                    <div class="col-sm-3">
                        <label class="control-label" for="email">Email</label>
                    </div>
                    <div class="col-sm-7">
                        <form:input path="email" id="email" name="email" type="text" placeholder="abc@mail.com" class="form-control input-md"/>
                        <form:errors path="email" cssClass="alert-danger"></form:errors>
                    </div>
                </div>

                <!-- Textarea -->
                <div class="form-group row col-sm-12">
                    <div class="col-sm-3">
                        <label class="control-label" for="text">Message</label>
                    </div>
                    <div class="col-sm-7">
                        <form:textarea path="text" class="form-control" id="text" name="text"></form:textarea>
                        <form:errors path="text" cssClass="alert-danger"></form:errors>
                    </div>
                </div>

                <!-- Button -->
                <div class="form-group row col-sm-12">
                    <div class="col-sm-6">
                        <label class="control-label" for="submit"></label>
                    </div>
                    <div class="col-sm-3">
                        <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
                    </div>
                </div>


            </fieldset>
        </form:form>
    </div>
    <div class="col-sm-2"></div>
</div>
</body>
</html>
