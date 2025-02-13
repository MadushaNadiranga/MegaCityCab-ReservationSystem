package com.reservation.servlet;

import com.reservation.dao.BookingDAO;
import com.reservation.model.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/manageBooking")
public class BookingServlet extends HttpServlet {
    private BookingDAO bookingDAO;

    @Override
    public void init() throws ServletException {
        bookingDAO = new BookingDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Booking> bookings = bookingDAO.getAllBookings();
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("manageBooking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
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
            Booking booking = new Booking(customerName, customerEmail, nic, phone, driverId, vehicleId, pickupLocation, dropoffLocation, bookingDate, totalAmount, paymentStatus);

            // Add booking to database
            boolean success = bookingDAO.addBooking(booking);

            // Redirect back with success or failure message
            response.sendRedirect("manageBooking.jsp?added=" + (success ? "1" : "0"));

        } else if ("cancel".equals(action)) {
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            boolean success = bookingDAO.cancelBooking(bookingId);
            response.sendRedirect("manageBooking.jsp?cancel=" + (success ? "1" : "0"));
        }
    }
}
