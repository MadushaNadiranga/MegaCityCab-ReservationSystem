package com.reservation.dao;

import java.sql.*;
import com.reservation.model.User;
import com.reservation.config.DatabaseConnection;

public class UserDAO {
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (full_name, username, email, password, phone, role, profile_picture) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getRole());
            pstmt.setString(7, user.getProfilePicture());

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
