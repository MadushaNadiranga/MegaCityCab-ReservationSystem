<!DOCTYPE html>
<html>
<head>
    <title>Add New Booking</title>
    <link rel="stylesheet" type="text/css" href="addDrivers.css">
</head>
<body>

<div class="container">
    <h2>Add New Booking</h2>

    <% if (request.getParameter("success") != null) { %>
    <p class="success">Booking added successfully!</p>
    <% } else if (request.getParameter("error") != null) { %>
    <p class="error">Error adding booking. Please try again.</p>
    <% } %>

    <form action="BookingServlet" method="post">
        <label>Customer Name:</label>
        <input type="text" name="customer_name" required>

        <label>Customer Email:</label>
        <input type="email" name="customer_email" required>

        <label>NIC:</label>
        <input type="text" name="nic" required>

        <label>Phone:</label>
        <input type="text" name="phone" required>

        <label>Driver ID:</label>
        <input type="text" name="driver_id">

        <label>Vehicle ID:</label>
        <input type="text" name="vehicle_id">

        <label>Pickup Location:</label>
        <input type="text" name="pickup_location" required>

        <label>Drop-off Location:</label>
        <input type="text" name="dropoff_location" required>

        <label>Booking Date and Time:</label>
        <input type="datetime-local" name="booking_date" required>

        <label>Total Amount:</label>
        <input type="text" name="total_amount">

        <label>Payment Status:</label>
        <select name="payment_status" required>
            <option value="cash">Cash</option>
            <option value="card">Card</option>
        </select>

        <input type="submit" value="Add Booking">
    </form>
</div>

</body>
</html>