package com.reservation.servlet;

import com.reservation.dao.BookingDAO;
import com.reservation.model.Booking;
import com.reservation.util.EmailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/manageBooking")
public class BookingServlet extends HttpServlet {
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        bookingDAO = new BookingDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            try {
                // Get form data
                String customerName = request.getParameter("customer_name");
                String customerEmail = request.getParameter("customer_email");
                String nic = request.getParameter("nic");
                String phone = request.getParameter("phone");
                int driverId = Integer.parseInt(request.getParameter("driver_id"));
                int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
                String pickupLocation = request.getParameter("pickup_location");
                String dropoffLocation = request.getParameter("dropoff_location");
                LocalDateTime bookingDate = LocalDateTime.parse(request.getParameter("booking_date"));
                double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
                String paymentStatus = request.getParameter("payment_status");

                // Create Booking object
                Booking booking = new Booking(customerName, customerEmail, nic, phone, driverId, vehicleId,
                        pickupLocation, dropoffLocation, bookingDate, totalAmount, paymentStatus);

                // Add booking to database
                boolean success = bookingDAO.addBooking(booking);

                if (success) {
                    // Send confirmation email
                    String subject = "Booking Confirmation - MegaCity Cab";
                    String body = "Dear " + customerName + ",\n\n" +
                            "Your booking has been confirmed with the following details:\n" +
                            "Pickup Location: " + pickupLocation + "\n" +
                            "Drop-off Location: " + dropoffLocation + "\n" +
                            "Booking Date: " + bookingDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" +
                            "Total Amount: $" + totalAmount + "\n" +
                            "Payment Status: " + paymentStatus + "\n\n" +
                            "Thank you for choosing MegaCity Cab!";

                    EmailUtil.sendEmail(customerEmail, subject, body);
                }

                // Redirect back with success or failure message
                response.sendRedirect("manageBooking.jsp?added=" + (success ? "1" : "0"));

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("manageBooking.jsp?added=0");
            }
        }
    }
}