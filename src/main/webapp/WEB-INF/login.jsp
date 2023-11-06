<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/index.css" />
    <title>Login/Register - Sample Hunters</title>
    <link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/800px-SNice.svg.png"
    type="image/png" >
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand" href="/">Sample Hunters</a>
        <div class="navbar-right" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${empty sessionScope.user_id}">
                            <a class ="nav-link" href = "/login">Login to add a sample!</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="/addSample">+ Add Sample</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${empty sessionScope.user_id}">
                            <a class="nav-link" href="/login">Login</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="/logout">Logout</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="header">
            <h1>Sample Hunters</h1>
        </div>
        <div class="form-container">
            <div class="form left">
                <div class="header">
                    <h1>New User Registration</h1>
                </div>
                <form:form action="/register" modelAttribute="newUser">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <form:input path="userName" type="text" class="form-control" id="username" placeholder="Username" />
                        <form:errors path="userName" cssClass="alert alert-danger" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <form:input path="email" type="email" class="form-control" id="email" placeholder="Email" />
                        <form:errors path="email" cssClass="alert alert-danger" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <form:input path="password" type="password" class="form-control" id="password" placeholder="Password" />
                        <form:errors path="password" cssClass="alert alert-danger" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="confirm">Confirm Password</label>
                        <form:input path="confirm" type="password" class="form-control" id="confirm" placeholder="Confirm Password" />
                        <form:errors path="confirm" cssClass="alert alert-danger" element="div" />
                    </div>
                    <div class="text-center mt-4">
                        <input type="submit" value="Create Account" class="btn btn-primary" />
                    </div>
                </form:form>
            </div>
            <div class="form right">
                <div class="header">
                    <h1>Login</h1>
                </div>
                <form:form action="/login" modelAttribute="newLogin">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <form:input path="email" type="email" class="form-control" id="email" placeholder="Email" />
                        <form:errors path="email" cssClass="alert alert-danger" element="div" />
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <form:input path="password" type="password" class="form-control" id="password" placeholder="Password" />
                        <form:errors path="password" cssClass="alert alert-danger" element="div" />
                    </div>
                    <div class="text-center mt-4">
                        <input type="submit" value="Login" class="btn btn-primary" />
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
