<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<div>

    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div class="text-center">
        <form class="form-signin" action="${pageContext.request.contextPath}/login" onsubmit="return loginValidate()" method="post">
            <div>
                <h2 class="form-signin-heading">Please sign in</h2>
            </div>
            <div>
                <input class="input-block-level" id="userName" name="userName" type="text" placeholder="user name" class="form-control input-md"/>
            </div>
            <div>
                <input class="input-block-level" id="password" name="password" type="password" placeholder="password" class="form-control input-md"/>
            </div>
            <div>
                <button id="submit" name="submit" class="btn btn-primary">Log in</button>
            </div>
        </form>
    </div>


</div>


