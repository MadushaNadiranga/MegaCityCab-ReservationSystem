<%@ page import="com.reservation.dao.BookingDAO, com.reservation.model.Booking, java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Bookings</title>
    <link rel="stylesheet" type="text/css" href="manageBooking.css">
</head>
<body>
<div class="container">
    <h2>Manage Bookings</h2>

    <%
        BookingDAO bookingDAO = new BookingDAO();
        List<Booking> bookings = bookingDAO.getAllBookings();
    %>

    <h3>Bookings List</h3>
    <div class="booking-container">
        <%
            if (bookings == null || bookings.isEmpty()) {
        %>
        <p class="error">No bookings found.</p>
        <%
        } else {
            for (Booking booking : bookings) {
        %>
        <div class="booking-card">
            <p>Booking ID: <%= booking.getBookingId() %></p>
            <p>Customer: <%= booking.getCustomerName() %> (<%= booking.getCustomerEmail() %>)</p>
            <p>NIC: <%= booking.getNic() %></p>
            <p>Phone: <%= booking.getPhone() %></p>
            <p>Driver ID: <%= booking.getDriverId() %> | Vehicle ID: <%= booking.getVehicleId() %></p>
            <p>Pickup: <%= booking.getPickupLocation() %></p>
            <p>Drop-off: <%= booking.getDropoffLocation() %></p>
            <p>Date & Time: <%= booking.getBookingDate() %></p>
            <p>Total Amount: $<%= booking.getTotalAmount() %></p>
            <p>Payment Status: <%= booking.getPaymentStatus() %></p>

            <form action="bookingManagement" method="post">
                <input type="hidden" name="booking_id" value="<%= booking.getBookingId() %>">
                <input type="hidden" name="action" value="cancel">
                <button type="submit" class="cancel-btn">Cancel Booking</button>
            </form>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
