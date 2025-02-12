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

@WebServlet("/loginPage")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    public LoginServlet() {
        this.userDAO = new UserDAO(); // Initialize the UserDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get username and password from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get the user from the database by username
        User user = userDAO.getUserByUsername(username);

        if (user != null && HashUtil.hashPassword(password).equals(user.getPassword())) {
            // User exists and password matches
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Store user object in session

            // Redirect to the dashboard page
            response.sendRedirect("adminDashboard.jsp");
        } else {
            // Incorrect username or password
            response.sendRedirect("loginPage.jsp?error=1"); // Error message for invalid credentials
        }
    }
}
