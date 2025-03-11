<!DOCTYPE html>
<html>
<head>
  <title>Add New Vehicle</title>
  <link rel="stylesheet" type="text/css" href="addDrivers.css">
</head>
<body>

<div class="container">
  <h2>Add New Vehicle</h2>

  <%-- Success or error messages --%>
  <% if (request.getParameter("success") != null) { %>
  <p class="success">Vehicle added successfully!</p>
  <% } else if (request.getParameter("error") != null) { %>
  <p class="error">Error adding vehicle. Please try again.</p>
  <% } %>

  <form action="VehicleServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="add">
    <label>Model:</label>
    <input type="text" name="model" required>

    <label>Year:</label>
    <input type="number" name="year" required>

    <label>License Plate:</label>
    <input type="text" name="license_plate" required>

    <label>Status:</label>
    <select name="status" required>
      <option value="available">Available</option>
      <option value="in_service">In Service</option>
      <option value="booked">Booked</option>
      <option value="unavailable">Unavailable</option>
    </select>

    <label>Vehicle Image:</label>
    <input type="file" name="vehicle_image" required>

    <input type="submit" value="Add Vehicle">
  </form>
</div>

</body>
</html>