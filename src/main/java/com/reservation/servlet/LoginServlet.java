package com.reservation.servlet;

import com.reservation.dao.UserDAO;
import com.reservation.model.User;
import com.reservation.util.HashUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    public LoginServlet() {
        this.userDAO = new UserDAO(); // Initialize the UserDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.getUserByUsername(username);

        if (user != null && HashUtil.hashPassword(password).equals(user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("adminDashboard.jsp");
        } else {
            response.sendRedirect("loginPage.jsp?error=1");
        }
    }
}
