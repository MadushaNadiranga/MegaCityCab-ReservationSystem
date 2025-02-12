<!DOCTYPE html>
<html>
<head>
    <title>Add New Driver</title>
    <link rel="stylesheet" type="text/css" href="addDrivers.css">
</head>
<body>

<div class="container">
    <h2>Add New Driver</h2>

    <% if (request.getParameter("success") != null) { %>
    <p class="success">Driver added successfully!</p>
    <% } else if (request.getParameter("error") != null) { %>
    <p class="error">Error adding driver. Please try again.</p>
    <% } %>

    <form action="DriverServlet" method="post">
        <label>Name:</label>
        <input type="text" name="name" required>

        <label>Phone:</label>
        <input type="text" name="phone" required>

        <label>License Number:</label>
        <input type="text" name="license_number" required>

        <label>Assigned Vehicle ID (optional):</label>
        <input type="text" name="assigned_vehicle_id">

        <label>Status:</label>
        <select name="status" required>
            <option value="active">Active</option>
            <option value="inactive">Inactive</option>
            <option value="suspended">Suspended</option>
        </select>

        <input type="submit" value="Add Driver">
    </form>
</div>

</body>
</html>
