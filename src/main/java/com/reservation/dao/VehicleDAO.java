
package com.reservation.dao;

import com.reservation.model.Vehicle;
import com.reservation.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    // Add vehicle to the database
    public boolean addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (model, year, license_plate, status, vehicle_image) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vehicle.getModel());
            pstmt.setInt(2, vehicle.getYear());
            pstmt.setString(3, vehicle.getLicensePlate());
            pstmt.setString(4, vehicle.getStatus());
            pstmt.setString(5, vehicle.getVehicleImage());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Additional methods (getAllVehicles, updateVehicle, deleteVehicle) can be implemented similarly
}