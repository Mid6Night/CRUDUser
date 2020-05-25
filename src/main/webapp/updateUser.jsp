<%@ page import="com.mid6night.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 25.05.2020
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
User user = (User) request.getAttribute("user");
%>
<form action="/update<%=(request.getParameter("id") == null)? "": "?id=" + request.getParameter("id")%>"
      method="post">
    <h1>Name</h1>
    <input type="text" name="name" value="<%=(user != null)?user.getName() : "" %>">
    <h1>Age</h1>
    <input type="text" name="age" value="<%=(user != null)?user.getAge() : "" %>">
    <button type="submit">Save</button>
</form>
</body>
</html>
