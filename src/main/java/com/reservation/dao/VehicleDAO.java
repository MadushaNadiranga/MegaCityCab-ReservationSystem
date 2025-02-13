
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

    /// Fetch all vehicles from the database
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getInt("vehicle_id"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getString("license_plate"),
                        rs.getString("status"),
                        rs.getString("vehicle_image")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Update vehicle details
    public boolean updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE vehicles SET model = ?, year = ?, license_plate = ?, status = ? WHERE vehicle_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vehicle.getModel());
            pstmt.setInt(2, vehicle.getYear());
            pstmt.setString(3, vehicle.getLicensePlate());
            pstmt.setString(4, vehicle.getStatus());
            pstmt.setInt(5, vehicle.getVehicleId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a vehicle
    public boolean deleteVehicle(int vehicleId) {
        String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vehicleId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}