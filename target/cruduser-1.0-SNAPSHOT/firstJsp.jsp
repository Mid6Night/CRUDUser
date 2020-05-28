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
        <th>AGE</th>
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
            <%= user.getAge()%>
        </td>
        <td>
            <a href="/update?id=<%=user.getId()%>">
                UPDATE
            </a>
        </td>
        <td>
            <a href="/delete?id=<%=user.getId()%>">
                DELETE
            </a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<a href="/update">
    <button>
        Create
    </button>
</a>
</body>
</html>
