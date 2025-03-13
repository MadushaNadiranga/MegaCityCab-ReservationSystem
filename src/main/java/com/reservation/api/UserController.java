package com.reservation.api;

import com.reservation.config.DatabaseConnection;
import com.reservation.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/api/user")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT full_Name, username, email, phone, role, profile_Picture FROM users";
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                StringBuilder json = new StringBuilder("[");
                while (rs.next()) {
                    json.append("{\"")
                            .append("fullName\": \"").append(rs.getString("fullName")).append("\",")
                            .append("\"username\": \"").append(rs.getString("username")).append("\",")
                            .append("\"email\": \"").append(rs.getString("email")).append("\",")
                            .append("\"phone\": \"").append(rs.getString("phone")).append("\",")
                            .append("\"role\": \"").append(rs.getString("role")).append("\",")
                            .append("\"profilePicture\": \"").append(rs.getString("profilePicture")).append("\"},");
                }
                if (json.charAt(json.length() - 1) == ',') {
                    json.deleteCharAt(json.length() - 1);
                }
                json.append("]");
                out.print(json);
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String fullName = req.getParameter("fullName");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");
        String profilePicture = req.getParameter("profilePicture");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (full_Name, username, email, password, phone, role, profile_Picture) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, fullName);
                stmt.setString(2, username);
                stmt.setString(3, email);
                stmt.setString(4, password);
                stmt.setString(5, phone);
                stmt.setString(6, role);
                stmt.setString(7, profilePicture);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    out.print("{\"message\": \"User created successfully!\"}");
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\": \"Failed to create user\"}");
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Database error: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
}

// Let me know if you want any adjustments or additional features! 🚀
