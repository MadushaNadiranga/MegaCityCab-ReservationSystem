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

    <form action="BookingManagement" method="post">
        <input type="hidden" name="action" value="add">

        <label for="customer_name">Customer Name:</label>
        <input type="text" id="customer_name" name="customer_name" required>

        <label for="customer_email">Customer Email:</label>
        <input type="email" id="customer_email" name="customer_email" required>

        <label for="nic">NIC:</label>
        <input type="text" id="nic" name="nic">

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone">

        <label for="driver_id">Driver ID:</label>
        <input type="number" id="driver_id" name="driver_id">

        <label for="vehicle_id">Vehicle ID:</label>
        <input type="number" id="vehicle_id" name="vehicle_id">

        <label for="pickup_location">Pickup Location:</label>
        <input type="text" id="pickup_location" name="pickup_location">

        <label for="dropoff_location">Dropoff Location:</label>
        <input type="text" id="dropoff_location" name="dropoff_location">

        <label for="booking_date">Booking Date:</label>
        <input type="datetime-local" id="booking_date" name="booking_date">

        <label for="total_amount">Total Amount:</label>
        <input type="text" id="total_amount" name="total_amount">

        <label>Payment Status:</label>
        <select name="payment_status" required>
            <option value="cash">Cash</option>
            <option value="card">Card</option>
        </select>

        <button type="submit">Add Booking</button>
    </form>

</body>
</html>