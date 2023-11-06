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
    <link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/800px-SNice.svg.png"
    type="image/png" >
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <img class="navbar-brand-logo" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/800px-SNice.svg.png" alt="Sample Hunters Logo">
        <a class="navbar-brand" href="/">Sample Hunters</a>
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
        <div id="content" class="mt-2">
            <div class="row">
                <div class="col-md-4 mb-4">
                    <h1>Sample:</h1>
                    <h3>${sample.sampleName} - </h3>
                    <h3>${sample.sampleArtist}</h3>
                    <div id="player"></div>
                </div>
                <div class="col-md-3 text-center image-container">
                    <img src="/img/arrow@2x.png" alt="Arrow Image">
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
                    <h1>Where to hear it:</h1>
                    <div class="sample-description">
                        <p>${sample.sampleDescription}</p>
                    </div>
                    
                </div>
            </div>
        </div>
        <div class="header">
            <h3>Found by: ${sample.user.userName}</h3>
        </div>
        <div class="row mb-3">
            <div class="col-md-12 d-flex flex-row">
                <a href="/previousSample/${sample.id}" class="btn btn-primary mx-2">Previous</a>
                <a href="/nextSample/${sample.id}" class="btn btn-primary mx-2">Next</a>
            </div>
        </div>
        <c:if test="${sessionScope.user_id ne null && sample.user.id eq sessionScope.user_id}">
            <div class="row mb-3">
                <div class="col-md-12 d-flex flex-row">
                    <a href="/samples/${sample.id}/edit" class="btn btn-primary mx-2">Edit</a>
                    <a href="/samples/${sample.id}/delete" class="btn btn-danger mx-2">Delete</a>
                </div>
            </div>
        </c:if>
            <a href="/home" class="btn btn-secondary">Back to Home</a>
        </div>
    </div>
    <!-- Include the YouTube API script -->
    <script src="https://www.youtube.com/iframe_api?api_key=AIzaSyBenzKGIH5McqL1TWMirdlPS21YJqih0mA"></script>
    <!-- Include your custom JavaScript file -->
    <script src="/js/showSample.js"></script>

    <script>
        var sampleLink = "${sample.sampleLink}";
        var sampleSourceLink = "${sample.sampleSourceLink}";
    </script>
</body>
</html>
