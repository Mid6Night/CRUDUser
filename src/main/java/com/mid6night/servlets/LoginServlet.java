package com.mid6night.servlets;

import com.mid6night.Services.Service;
import com.mid6night.Services.UserService;
import com.mid6night.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = Service.getInstance();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = userService.findUser(name, password);

        if (user == null) {
            return;
        }
        req.getSession().setAttribute("user", user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
    }

}
