package com.mid6night.servlets;

import com.mid6night.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        User user = (User) session.getAttribute("user");
        if (user == null && !req.getRequestURL().toString().contains("login") ||
                user != null && req.getRequestURL().toString().contains("login")) {
            resp.setStatus(401);
            return;
        }
        if (user == null){
            user = new User();
            user.setRole("user");
        }
        if (req.getRequestURL().toString().contains("admin") && !user.getRole().equals("admin")) {
            resp.setStatus(401);
            return;
        }

        if (request.getParameter("logout") != null) {
            session.invalidate();
        }

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }
}
