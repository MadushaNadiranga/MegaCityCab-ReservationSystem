package com.reservation.servlet;

import com.reservation.dao.UserDAO;
import com.reservation.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userManagement")
public class UserManagement extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addUser(request, response);
        } else if ("delete".equals(action)) {
            deleteUser(request, response);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String profilePicture = request.getParameter("profilePicture");

        User user = new User(fullName, username, email, password, phone, role, profilePicture);
        userDAO.addUser(user);
        response.sendRedirect("manageUser.jsp");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        userDAO.deleteUser(username);
        response.sendRedirect("manageUsers.jsp");
    }
}
