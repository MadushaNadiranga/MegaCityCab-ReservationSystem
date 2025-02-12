package com.reservation.dao;

import com.reservation.model.Driver;
import com.reservation.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    // Add driver to the database
    public boolean addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (name, phone, license_number, assigned_vehicle_id, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, driver.getName());
            pstmt.setString(2, driver.getPhone());
            pstmt.setString(3, driver.getLicenseNumber());
            if (driver.getAssignedVehicleId() != null) {
                pstmt.setInt(4, driver.getAssignedVehicleId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setString(5, driver.getStatus());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all drivers from the database
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("license_number"),
                        rs.getInt("assigned_vehicle_id"),
                        rs.getString("status")
                );
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // Get driver by ID
    public Driver getDriverById(int driverId) {
        String sql = "SELECT * FROM drivers WHERE driver_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, driverId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Driver(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("license_number"),
                        rs.getInt("assigned_vehicle_id"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update driver information
    public boolean updateDriver(Driver driver) {
        String sql = "UPDATE drivers SET name = ?, phone = ?, license_number = ?, assigned_vehicle_id = ?, status = ? WHERE driver_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, driver.getName());
            pstmt.setString(2, driver.getPhone());
            pstmt.setString(3, driver.getLicenseNumber());
            if (driver.getAssignedVehicleId() != null) {
                pstmt.setInt(4, driver.getAssignedVehicleId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setString(5, driver.getStatus());
            pstmt.setInt(6, driver.getDriverId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete driver from the database
    public boolean deleteDriver(int driverId) {
        String sql = "DELETE FROM drivers WHERE driver_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, driverId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

