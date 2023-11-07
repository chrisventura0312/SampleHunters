<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/index.css" />
    <title>Sample Hunters</title>
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
    <div class="container mt-4">
        <div class="header">
            <h1>Welcome ${userName} :D</h1>
        </div>
        <div id="content" class="mt-4">
            <div class="table">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Sample Name</th>
                            <th>Sample Artist</th>
                            <th>Found by:</th>
                            <th>Who Sampled?</th>
                            <th>Likes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allSamples}" var="sample">
                            <tr>
                                <td><a href="/samples/${sample.id}" class="link-to-sample">${sample.sampleName}</a></td>
                                <td>${sample.sampleArtist}</td>
                                <td>${sample.user.userName}</td>
                                <td>${sample.sampleSourceArtist}</td>
                                <td>${sample.likesCount}</td>
                                <c:if test="${sessionScope.user_id ne null && sample.user.id eq sessionScope.user_id}">
                                    <td class="last-column">
                                        <a href="/samples/${sample.id}/edit" class="edit-button">Edit</a> 
                                        <a href="/samples/${sample.id}/delete" class="delete-button ">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
