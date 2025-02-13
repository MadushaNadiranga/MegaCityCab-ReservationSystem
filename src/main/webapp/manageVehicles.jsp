<%@ page import="com.reservation.dao.VehicleDAO, com.reservation.model.Vehicle, java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Manage Vehicles</title>
  <link rel="stylesheet" type="text/css" href="manageVehicles.css">
</head>
<body>
<div class="container">
  <h2>Manage Vehicles</h2>

  <%
    VehicleDAO vehicleDAO = new VehicleDAO();
    List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
  %>

  <div class="vehicle-container">
    <%
      if (vehicles == null || vehicles.isEmpty()) {
    %>
    <p class="error">No vehicles found.</p>
    <%
    } else {
      for (Vehicle vehicle : vehicles) {
    %>
    <div class="vehicle-card">
      <img src="<%= vehicle.getVehicleImage() %>" alt="Vehicle Image" class="vehicle-image">
      <p><strong>Model:</strong> <%= vehicle.getModel() %></p>
      <p><strong>Year:</strong> <%= vehicle.getYear() %></p>
      <p><strong>License Plate:</strong> <%= vehicle.getLicensePlate() %></p>
      <p><strong>Status:</strong> <%= vehicle.getStatus() %></p>

      <!-- Update Form -->
      <form action="VehicleServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="vehicle_id" value="<%= vehicle.getVehicleId() %>">
        <input type="text" name="model" value="<%= vehicle.getModel() %>" required>
        <input type="number" name="year" value="<%= vehicle.getYear() %>" required>
        <input type="text" name="license_plate" value="<%= vehicle.getLicensePlate() %>" required>
        <select name="status">
          <option value="Available" <%= vehicle.getStatus().equals("Available") ? "selected" : "" %>>Available</option>
          <option value="Unavailable" <%= vehicle.getStatus().equals("Unavailable") ? "selected" : "" %>>Unavailable</option>
        </select>
        <button type="submit" class="update-btn">Update</button>
      </form>

      <!-- Delete Form -->
      <form action="VehicleServlet" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="vehicle_id" value="<%= vehicle.getVehicleId() %>">
        <button type="submit" class="delete-btn">Delete</button>
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
