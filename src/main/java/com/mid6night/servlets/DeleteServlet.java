package com.mid6night.servlets;

import com.mid6night.Services.UserService;
import com.mid6night.Services.UserServiceJdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = UserServiceJdbc.getUserServiceJdbc();
        userService.deleteUser(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
    }
}
