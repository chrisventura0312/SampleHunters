<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/index.css" />
    <title>Create Sample</title>
    <link rel="icon" href="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/SNice.svg/800px-SNice.svg.png"
    type="image/png" >
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <a class="navbar-brand" href="/">Sample Hunters</a>
        <div class="navbar-right" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="/home" class="nav-link">Home!</a>
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
        <h1 class="mb-4">Create Sample</h1>
        
        <form:form method="post" action="/addSample" modelAttribute="newSample">
            <div class="form-group">
                <label for="sampleName">Sample Name:</label>
                <form:input path="sampleName" cssClass="form-control" id="sampleName" />
                <form:errors path="sampleName" cssClass="alert alert-danger" element="div" />
            </div>
            
            <div class="form-group">
                <label for="sampleArtist">Sample Artist:</label>
                <form:input path="sampleArtist" cssClass="form-control" id="sampleArtist" />
                <form:errors path="sampleArtist" cssClass="alert alert-danger" element="div" />
            </div>
            
            <div class="form-group">
                <label for="sampleLink">Sample Link:</label>
                <form:input path="sampleLink" cssClass="form-control" id="sampleLink" />
                <form:errors path="sampleLink" cssClass="alert alert-danger" element="div" />
            </div>
            
            <div class="form-group">
                <label for="sampleSourceArtist">Who Sampled It?:</label>
                <form:input path="sampleSourceArtist" cssClass="form-control" id="sampleSourceArtist" />
                <form:errors path="sampleSourceArtist" cssClass="alert alert-danger" element="div" />
            </div>
            
            <div class="form-group">
                <label for="sampleSource">Where?:</label>
                <form:input path="sampleSource" cssClass="form-control" id="sampleSource" />
                <form:errors path="sampleSource" cssClass="alert alert-danger" element="div" />
            </div>
            
            <div class="form-group">
                <label for="sampleSourceLink">Link to Source:</label>
                <form:input path="sampleSourceLink" cssClass="form-control" id="sampleSourceLink" />
                <form:errors path="sampleSourceLink" cssClass="alert alert-danger" element="div" />
            </div>
            
            <div class="form-group">
                <label for="sampleDescription">Sample Description:</label>
                <form:textarea path="sampleDescription" cssClass="form-control large-textarea" id="sampleDescription" rows="6" cols="50"></form:textarea>
                <form:errors path="sampleDescription" cssClass="alert alert-danger" element="div" />
            </div>
            
            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">Create Sample</button>
            <a href="/home" class="btn btn-secondary">Cancel</a>
        </form:form>
    </div>
</body>
</html>
