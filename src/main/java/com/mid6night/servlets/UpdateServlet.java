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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private UserService userService = UserServiceJdbc.getUserServiceJdbc();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        if (req.getParameter("id") == null) {
            userService.addUser(user);
        } else {
            user.setId(Long.parseLong(req.getParameter("id")));
            userService.updateUser(user);
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        if (req.getParameter("id") != null) {
            req.setAttribute("user", UserJdbcDAO.getInstance()
                    .getUser(Long.parseLong(req.getParameter("id"))));
        }
        getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
    }
}
