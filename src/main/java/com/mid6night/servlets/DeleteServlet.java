package com.mid6night.servlets;

import com.mid6night.dao.UserJdbcDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJdbcDAO userJdbcDAO = UserJdbcDAO.getInstance();
        userJdbcDAO.deleteUser(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
    }
}
