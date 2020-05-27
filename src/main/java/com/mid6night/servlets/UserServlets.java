package com.mid6night.servlets;

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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJdbcDAO userJdbcDAO = UserJdbcDAO.getInstance();

        List<User> users = userJdbcDAO.getAllUser();

        req.setAttribute("users",users);

        getServletContext().getRequestDispatcher("/firstJsp.jsp").forward(req, resp);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
    }
}
