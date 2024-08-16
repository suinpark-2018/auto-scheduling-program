<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
<body>
    <div class="login-container">
        <h2>Sign In</h2>
        <form action="/login/in" method="POST">
            <p id="error-msg">${errorMsg}</p>
            <div class="input-group">
                <label for="id">ID</label>
                <input type="text" id="id" name="id" value="${cookie['id'].value}" placeholder="Enter your ID" required>
                <p id="non-exist-id-msg">${nonExistIdMsg}</p>
            </div>
            <div class="input-group">
                <label for="pwd">Password</label>
                <input type="password" id="pwd" name="pwd" placeholder="Enter your password" required>
                <p id="not-match-msg">${notMatchMsg}</p>
            </div>
            <div class="checkbox-group">
                <input type="checkbox" id="rememberMe" name="rememberMe" ${cookie['id'] != null ? 'checked' : ''}>
                <label for="rememberMe">Remember Me</label>
            </div>
            <button type="submit" class="btn primary">Sign In</button>
            <div class="additional-options">
                <a href="#" class="forgot-link">Forgot ID/Password?</a>
                <a href="/register/form" class="signup-link">Sign Up</a>
            </div>
        </form>
        <div class="social-login">
            <p>Or sign in with</p>
            <button class="btn social naver" onclick="location.href='#'">Naver</button>
            <button class="btn social kakao" onclick="location.href='#'">Kakao</button>
            <button class="btn social google" onclick="location.href='#'">Google</button>
        </div>
    </div>
</body>
</html>
