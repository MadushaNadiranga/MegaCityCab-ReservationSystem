package com.reservation.dao;
import java.util.*;
import java.sql.*;

import com.reservation.model.User;
import com.reservation.config.DatabaseConnection;
import com.reservation.util.HashUtil;

public class UsersDAO implements IUserDAO {

    // Store users in different collections
    private List<User> userList = new LinkedList<>(); // Using LinkedList instead of ArrayList
    private Set<User> userSet = new HashSet<>(); // Ensuring unique users
    private Map<String, User> userMap = new HashMap<>(); // Store users by username for quick retrieval

    // Add user to the database and store in collections
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (full_name, username, email, password, phone, role, profile_picture) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, HashUtil.hashPassword(user.getPassword())); // Store hashed password
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getRole());
            pstmt.setString(7, user.getProfilePicture());

            if (pstmt.executeUpdate() > 0) {
                // Add to collections
                userList.add(user);
                userSet.add(user);
                userMap.put(user.getUsername(), user);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all users from the database
    public List<User> getAllUsers() {
        userList.clear(); // Clear previous data
        userSet.clear();
        userMap.clear();

        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("profile_picture")
                );
                userList.add(user);
                userSet.add(user);
                userMap.put(user.getUsername(), user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList; // Return LinkedList of users
    }

    // Get user by username using HashMap for quick lookup
    public User getUserByUsername(String username) {
        if (userMap.containsKey(username)) {
            return userMap.get(username);
        }
        // If not in map, fetch from database
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User(
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("profile_picture")
                );
                userMap.put(username, user); // Store for future lookups
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }

    // Update user information
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET full_name = ?, email = ?, password = ?, phone = ?, role = ?, profile_picture = ? WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, HashUtil.hashPassword(user.getPassword()));
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getRole());
            pstmt.setString(6, user.getProfilePicture());
            pstmt.setString(7, user.getUsername());

            if (pstmt.executeUpdate() > 0) {
                // Update in collections
                userMap.put(user.getUsername(), user);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete user
    public boolean deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            if (pstmt.executeUpdate() > 0) {
                // Remove from collections
                userMap.remove(username);
                userList.removeIf(user -> user.getUsername().equals(username));
                userSet.removeIf(user -> user.getUsername().equals(username));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
