package com.mid6night.servlets;

import com.mid6night.Services.UserService;
import com.mid6night.Services.UserServiceJdbc;
import com.mid6night.dao.UserJdbcDAO;
import com.mid6night.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/")
public class UserServlets extends HttpServlet {
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = UserServiceJdbc.getUserServiceJdbc();

        List<User> users = userService.getAllUser();

        req.setAttribute("users",users);

        getServletContext().getRequestDispatcher("/firstJsp.jsp").forward(req, resp);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
    }
}
