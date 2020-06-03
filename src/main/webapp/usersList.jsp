<%@ page import="java.util.ArrayList" %>
<%@ page import="com.mid6night.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: nitro
  Date: 23.05.2020
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>Password</th>
        <th>Role</th>
        <th>UPDATE</th>
        <th>DELETE</th>
    </tr>
    <%
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
        for (User user : users) {
    %>
    <tr>
        <td>
            <%= user.getId()%>
        </td>
        <td>
            <%= user.getName()%>
        </td>
        <td>
            <%= user.getPassword()%>
        </td>
        <td>
            <%= user.getRole()%>
        </td>
        <td>
            <a href="/admin/update?id=<%=user.getId()%>">
                UPDATE
            </a>
        </td>
        <td>
            <a href="/admin/delete?id=<%=user.getId()%>">
                DELETE
            </a>
        </td>
    </tr>
    <%
        }
    %>
</table>

<h3>
    Add new user
</h3>
<form action="/admin/delete"  method="post">
    <h3>login</h3>
    <input type="text" required name="name" >
    <h3>password</h3>
    <input type="text" required name="password">
    <h3>role</h3>
    <input type="text" required name="role">
    <button type="submit">add</button>
</form>
<form action="/" method="post">
    <input type="submit" name="logout" value="Logout" />
</form>
</body>
</html>
