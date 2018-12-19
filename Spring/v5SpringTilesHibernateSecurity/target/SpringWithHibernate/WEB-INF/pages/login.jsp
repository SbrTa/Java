<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>



<div>

    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
<%--    <div class="text-center">
        <form class="form-signin" action="${pageContext.request.contextPath}/login/done" onsubmit="return loginValidate()" method="post">
            <div class="float-right">
                <span style="color:red;">${password}</span>
            </div>
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
    </div>--%>

    <div onload="document.f.username.focus();" class="text-center">
            <c:if test="${not empty error}">
                <div class="error">
                    <span style="color:red;">${error}</span>
                </div>
            </c:if>
            <c:if test="${not empty msg}">
                <span style="color:blue;">${msg}</span>
            </c:if>
        <form name="f" class="form-signin"
              action="<c:url value='j_spring_security_check' />" onsubmit="return loginValidate()" method="POST">
            <%--<div class="float-right">
                <span style="color:red;">${password}</span>
            </div>--%>
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
                <%--<button id="submit" name="submit" class="btn btn-primary">Log in</button>--%>
                    <input name="submit" type="submit" class="btn btn-primary" value="Login">
                    <%--<csrf disabled="true"/>--%>
                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
            </div>
        </form>
    </div>
    <%--<div onload="document.f.username.focus();">
        <h3>Login with Username and Password</h3>
        <form name="f" action="/login" method="POST">
            <table>
                <tbody>
                <tr><td>User:</td><td><input type="text" name="username" value=""></td></tr>
                <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
                <tr><td colspan="2"><input name="submit" type="submit" value="Login"></td></tr>
                <input name="_csrf" type="hidden" value="1435ed69-5b77-4ba1-9439-77f148855956">
                </tbody>
            </table>
        </form>
    </div>--%>


</div>


