<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" media="all" href="${pageContext.request.contextPath}/resources/css/global.css" />
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />
    <title>Login</title>
</head>
<body>
    <p>Please login to continue</p>
    <form action="login" method="POST">
        <input type="text" name="email" placeholder="Email" value="${email}" required />
        <input type="password" name="password" placeholder="Password" required />
        <input type="submit" />
    </form>
    <span class="err">${message_error}</span>
</body>
</html>