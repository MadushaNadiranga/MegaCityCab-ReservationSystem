package com.reservation.servlet;

import com.reservation.dao.UserDAO;
import com.reservation.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mega_city_cab", "root", "password");
            userDAO = new UserDAO(connection);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = userDAO.getAllUsers();
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/userList.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addUser(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    doGet(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(0, username, email, password);
        userDAO.addUser(newUser);
        response.sendRedirect("user");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(id, username, email, password);
        userDAO.updateUser(user);
        response.sendRedirect("user");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("user");
    }
}
