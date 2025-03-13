package com.reservation.servlet;

import com.reservation.dao.BookingDAO;
import com.reservation.model.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.reservation.util.EmailUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/BookingManagement")
public class BookingManagement extends HttpServlet {
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
        request.getRequestDispatcher("manageBookings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                String customerName = request.getParameter("customer_name");
                String customerEmail = request.getParameter("customer_email"); // Get the dynamic email
                String nic = request.getParameter("nic");
                String phone = request.getParameter("phone");

                int driverId = request.getParameter("driver_id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("driver_id"));
                int vehicleId = request.getParameter("vehicle_id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("vehicle_id"));

                String pickupLocation = request.getParameter("pickup_location");
                String dropoffLocation = request.getParameter("dropoff_location");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime bookingDate = LocalDateTime.parse(request.getParameter("booking_date"), formatter);

                double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
                String paymentStatus = request.getParameter("payment_status");

                Booking booking = new Booking(customerName, customerEmail, nic, phone, driverId, vehicleId, pickupLocation, dropoffLocation, bookingDate, totalAmount, paymentStatus);

                boolean success = bookingDAO.addBooking(booking);

                if (success) {
                    // Send confirmation email to customer
                    String emailSubject = "Booking Confirmation - Your Reservation Details";
                    String emailBody = "Dear " + customerName + ",\n\n"
                            + "Thank you for booking with us! Here are your reservation details:\n\n"
                            + "📌 Pickup Location: " + pickupLocation + "\n"
                            + "📍 Dropoff Location: " + dropoffLocation + "\n"
                            + "📅 Booking Date: " + bookingDate + "\n"
                            + "💰 Total Amount: $" + totalAmount + "\n"
                            + "💳 Payment Status: " + paymentStatus + "\n\n"
                            + "We look forward to serving you!\n\n"
                            + "Best regards,\n"
                            + "MEGA CITY CAB";

                    try {
                        EmailUtil.sendEmail(customerEmail, emailSubject, emailBody);
                        System.out.println("Booking confirmation email sent to: " + customerEmail);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error sending email to: " + customerEmail);
                    }

                    response.sendRedirect("addBooking.jsp?success=1");
                } else {
                    response.sendRedirect("addBooking.jsp?error=1");
                }


            } else if ("cancel".equals(action)) {
                int bookingId = Integer.parseInt(request.getParameter("booking_id"));
                boolean success = bookingDAO.cancelBooking(bookingId);
                response.sendRedirect("BookingManagement?action=view&cancel=" + (success ? "1" : "0"));

            } else if ("view".equals(action)) {
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
