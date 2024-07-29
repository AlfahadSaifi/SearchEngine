<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <style>
        body {
            overflow: hidden;
        }

        .container {
            font-family: Roboto, sans-serif;
            display: flex;
            flex-direction: row;
            height: 100vh;
            overflow: hidden;
        }

        .left {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f0f0f0;
        }

        .left img {
            max-width: 100%;
            height: auto;
        }

        .right {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            background-color: #d9e0e2;
        }

        .right h1 {
            margin-top: 20px;
        }

        .forgot-password {
            font-size: 20px;
            color: #1321da;
            text-align: center;
        }

        .login-form {
            width: 300px;
            padding: 40px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .login-form input[type="text"],
        .login-form input[type="password"],
        .login-form input[type="submit"] {
            width: 100%;
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-sizing: border-box;
            font-size: 18px;
        }

        .login-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        .error-red {
            color: red;
            margin-top: 5px;
            font-size: 20px;
        }

        .error-green {
            color: green;
            margin-top: 8px;
            font-size: 20px;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="left">
            <a href="${pageContext.request.contextPath}/">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqfy_ymD9F10Vd4KJStiezjapwwKwGjjmjvg&s" style="mix-blend-mode: multiply" alt="Logo">
            </a>
        </div>
        <div class="right">
            <h1>Nucleus Search</h1>
            <h3>Please Enter the Details:</h3>
            <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                <h2>Login</h2>
                <input type="text" name="username" placeholder="Username" required />
                <input type="password" name="password" placeholder="Password" required />
                <input type="submit" name="submitButton" value="Login" />
                <div class="forgot-password"><a href="#">Forgot password?</a></div>

                <c:if test="${not empty error}">
                    <div class="error-red">
                        ${error}
                    </div>
                </c:if>
                <c:if test="${not empty message}">
                    <div class="error-green">
                        ${message}
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</body>

</html>
