<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }
        .error-message {
            font-size: 24px;
            color: #dc3545;
            text-align: center;
        }

        .error-message img {
            width: 60%;
            max-width: 1280px;
            height: auto;
            margin-top: 2px;
        }
    </style>
</head>
<body>
    <div class="error-message">
        <h2>Oops! Something went wrong.</h2>
        <p>We apologize for the inconvenience. Please try again later.</p>
        <img src="${pageContext.request.contextPath}/static/errorPage.jpg" alt="Error Page">
    </div>
</body>
</html>
