<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/show.css" />
    <title>${sample.sampleName}</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand" href="/home">Sample Hunters</a>
        <div class="navbar-right" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${empty sessionScope.user_id}">
                            <a class="nav-link" href="/login">Login to add a sample!</a>
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
    <div class="container mt-4">
        <div id="content" class="mt-">
            <div class="row">
                <div class="col-md-6 mb-4">
                    <h1>Sample</h1>
                    <h3>${sample.sampleName} - </h3>
                    <h3>${sample.sampleArtist}</h3>
                    <div id="player"></div>
                </div>
                <div class="col-md-2 text-center image-container">
                    <img src="/img/arrow.png" alt="Arrow Image">
                </div>
                <div class="col-md-4">
                    <h1>Who Sampled?</h1>
                    <h3>${sample.sampleSourceArtist} - </h3>
                    <h3>${sample.sampleSource}</h3>
                    <div id="sourcePlayer"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <h3>Where to hear it:</h3>
                    <p>${sample.sampleDescription}</p>
                </div>
            </div>
        </div>
        <div class="header">
            <h3>Found by: ${sample.user.userName}</h3>
        </div>
        <div class="row">
            <div class="col-md-12">
                <a href="/home" class="btn btn-primary">Back to Home</a>
            </div>
        </div>
    </div>
    <!-- Include the YouTube API script -->
    <script src="https://www.youtube.com/iframe_api"></script>
    <!-- Include your custom JavaScript file -->
    <script src="/js/showSample.js"></script>

    <script>
        var sampleLink = "${sample.sampleLink}";
        var sampleSourceLink = "${sample.sampleSourceLink}";
    </script>
</body>
</html>
