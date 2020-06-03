package com.mid6night.servlets;

import com.mid6night.Services.Service;
import com.mid6night.Services.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = Service.getInstance();
        userService.deleteUser(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/admin");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
    }
}
