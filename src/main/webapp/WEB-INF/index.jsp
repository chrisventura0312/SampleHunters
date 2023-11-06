<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/index.css" />
    <title>Welcome to Sample Tracker - Sample Hunters</title>
    <link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/800px-SNice.svg.png" type="image/png">
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <img class="navbar-brand-logo" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/800px-SNice.svg.png" alt="Sample Hunters Logo">
        <a class="navbar-brand" href="/">Sample Hunters</a>
        <div class="navbar-right" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${empty sessionScope.user_id}">
                        <li class="nav-item">
                            <a class="nav-link" href="/home">Check us out!</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="/logout">Logout</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="header">
            <h1>Welcome to our Sample Tracker</h1>
        </div>
        <div class="form-container">
            <div class="form left">
                <div style="width:100%;height:0;padding-bottom:127%;position:relative;">
                    <iframe src="https://giphy.com/embed/BK1EfIsdkKZMY" width="100%" height="100%" style="position:absolute" frameborder="0" class="giphy-embed" allowfullscreen></iframe>
                </div>
            </div>
            <div class="welcome-message card p-3 flex-column align-items-center">
                <p>Welcome to our Sample Tracker, your ultimate destination for documenting the musical building blocks that shape your favorite tunes.
                    Get ready to embark on a sonic adventure where you can catalog and explore the very essence of your beloved tracks. 
                    Whether you're a dedicated sample sleuth or a curious music aficionado, our platform is your tool to unravel the mysteries behind the beats and melodies that make music special.
                    Dive in, start tracking, and be part of the music discovery journey!</p>
                    <a class="btn btn-primary mt-2" href="/home">Check us out!</a>
            </div>
            <div class="form right">
                <div style="width:100%;height:0;padding-bottom:127%;position:relative;">
                    <iframe src="https://giphy.com/embed/BK1EfIsdkKZMY" width="100%" height="100%" style="position:absolute" frameborder="0" class="giphy-embed" allowfullscreen></iframe>
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="card p-3 mt-4 d-flex flex-column align-items-center" >
                <a class="btn btn-primary mb-2" href="/login">Register</a>
                <p>Already have an account? <a href="/login">Login</a></p>
            </div>
        </div>
    </div>
</body>
</html>
