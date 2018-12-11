<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="row" style=""><div class="col-sm-4"><h3></h3></div><div class="col-sm-4 col-5"><h3></h3></div><div class="col-sm-4"><h3></h3></div></div>

<div class="container">
    <%--<div class="header clearfix">
        <nav>
            <ul class="nav nav-pills float-left">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
            <form class="nav nav-pills float-right" action="${pageContext.request.contextPath}/login" onsubmit="return loginValidate()" method="post">
                <div class="nav-item">
                    <input id="userName" name="userName" type="text" placeholder="user name" class="form-control input-md"/>
                </div>
                <div class="nav-item">
                    <input id="password" name="password" type="password" placeholder="password" class="form-control input-md"/>
                </div>
                    <div class="nav-item">
                    <input id="loginbtn" type="submit" style="padding: 5.8px" value="login"/>
                </div>
            </form>
        </nav>
    </div>--%>

    <div class="float-right">
        <span style="color:red;">${password}</span>
    </div>    <br/>
    <div class="jumbotron">
        <h1 class="display-3">Lorem ipsum</h1>
        <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget risus nec magna hendrerit vestibulum at et ex.
            Duis posuere elit ligula, sit amet bibendum purus suscipit ut. Aliquam erat volutpat. Aliquam sem nunc, pulvinar vitae massa et,
            ornare egestas tortor. Aliquam tristique in massa vel sollicitudin. Integer at luctus lacus, quis placerat lorem. Mauris at elit
            eget nisl dictum tempus sit amet nec mi. Phasellus interdum lorem ut mollis convallis.</p>

         <p class="lead">Curabitur quis nisl quam. In blandit erat eu ex cursus ornare. Donec quis dui laoreet, ultricies neque eget, consectetur tortor.
            Curabitur sagittis eleifend arcu quis cursus. Nulla pharetra iaculis augue sed cursus. Nunc bibendum nisl vitae augue luctus malesuada.
            Quisque dapibus velit augue, non consectetur metus tristique vel.</p>
        <%--<p>
            <a class="btn btn-lg btn-danger" href="${pageContext.request.contextPath}/signup" role="button">Sign up today</a>
            <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/loginpage" role="button">Log in</a>
        </p>--%>
        <p>
            <a href="${pageContext.request.contextPath}/signup">
                <img src="${pageContext.request.contextPath}/resources/icon/register.png"
                     class="img-thumbnail" width="250px" height="250px"
                     style="background-color:inherit; border:0px;"
                >
            </a>
            <a href="${pageContext.request.contextPath}/loginpage">
                <img src="${pageContext.request.contextPath}/resources/icon/login.png"
                     class="img-thumbnail" width="250px" height="250px"
                     style="background-color:inherit; border:0px;"
                >
            </a>
        </p>
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
        </div>
    </div>

    <br/>
    <br/>



    <br/>
    <br/>
</div><!-- /container -->