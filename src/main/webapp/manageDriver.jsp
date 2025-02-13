<%@ page import="java.util.List" %>
<%@ page import="com.reservation.dao.DriverDAO" %>
<%@ page import="com.reservation.model.Driver" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Drivers</title>
    <link rel="stylesheet" href="manageDriver.css">
    <script>
        function toggleEdit(driverId) {
            let card = document.getElementById("driver-card-" + driverId);
            let viewDiv = card.querySelector(".view-mode");
            let editDiv = card.querySelector(".edit-mode");
            viewDiv.style.display = viewDiv.style.display === 'none' ? 'block' : 'none';
            editDiv.style.display = editDiv.style.display === 'none' ? 'block' : 'none';
        }
    </script>
</head>
<body>
<h2>Manage Drivers</h2>
<div class="driver-container">
    <%
        DriverDAO driverDAO = new DriverDAO();
        List<Driver> drivers = driverDAO.getAllDrivers();
        for (Driver driver : drivers) {
    %>
    <div class="driver-card" id="driver-card-<%= driver.getDriverId() %>">
        <div class="view-mode">
            <h3><%= driver.getName() %></h3>
            <p>Phone: <%= driver.getPhone() %></p>
            <p>License: <%= driver.getLicenseNumber() %></p>
            <p>Assigned Vehicle: <%= driver.getAssignedVehicleId() != null ? driver.getAssignedVehicleId() : "None" %></p>
            <p>Status: <%= driver.getStatus() %></p>
            <button onclick="toggleEdit(<%= driver.getDriverId() %>)">Edit</button>
            <a href="deleteDriver?driverId=<%= driver.getDriverId() %>" class="delete-btn">Delete</a>
        </div>

        <div class="edit-mode" style="display: none;">
            <form action="updateDriver" method="post">
                <input type="hidden" name="driverId" value="<%= driver.getDriverId() %>">
                <input type="text" name="name" value="<%= driver.getName() %>" required>
                <input type="text" name="phone" value="<%= driver.getPhone() %>" required>
                <input type="text" name="license_number" value="<%= driver.getLicenseNumber() %>" required>
                <input type="text" name="assigned_vehicle_id" value="<%= driver.getAssignedVehicleId() != null ? driver.getAssignedVehicleId() : "" %>">
                <select name="status">
                    <option value="Active" <%= driver.getStatus().equals("Active") ? "selected" : "" %>>Active</option>
                    <option value="Inactive" <%= driver.getStatus().equals("Inactive") ? "selected" : "" %>>Inactive</option>
                </select>
                <button type="submit">Save</button>
                <button type="button" onclick="toggleEdit(<%= driver.getDriverId() %>)">Cancel</button>
            </form>
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>