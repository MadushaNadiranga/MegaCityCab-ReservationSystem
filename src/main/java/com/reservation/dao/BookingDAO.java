package com.reservation.dao;

import com.reservation.config.DatabaseConnection;
import com.reservation.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // Fetch all bookings from the database
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        rs.getString("nic"),
                        rs.getString("phone"),
                        rs.getInt("driver_id"),
                        rs.getInt("vehicle_id"),
                        rs.getString("pickup_location"),
                        rs.getString("dropoff_location"),
                        rs.getTimestamp("booking_date").toLocalDateTime(),
                        rs.getDouble("total_amount"),
                        rs.getString("payment_status")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Cancel booking by ID
    public boolean cancelBooking(int bookingId) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (customer_name, customer_email, nic, phone, driver_id, vehicle_id, pickup_location, dropoff_location, booking_date, total_amount, payment_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getCustomerName());
            stmt.setString(2, booking.getCustomerEmail());
            stmt.setString(3, booking.getNic());
            stmt.setString(4, booking.getPhone());
            stmt.setInt(5, booking.getDriverId());
            stmt.setInt(6, booking.getVehicleId());
            stmt.setString(7, booking.getPickupLocation());
            stmt.setString(8, booking.getDropoffLocation());
            stmt.setTimestamp(9, Timestamp.valueOf(booking.getBookingDate()));
            stmt.setDouble(10, booking.getTotalAmount());
            stmt.setString(11, booking.getPaymentStatus());

            return stmt.executeUpdate() > 0; // Returns true if at least one row is inserted

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
