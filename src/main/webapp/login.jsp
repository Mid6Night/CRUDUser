<%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 01.06.2020
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login"  method="post">
    <h1>login</h1>
    <input type="text" required name="name" >
    <h1>password</h1>
    <input type="text" required name="password">
    <button type="submit">Login</button>
</form>
</body>
</html>
