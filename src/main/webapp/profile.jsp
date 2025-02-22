<%@ page import="com.reservation.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>

<%
  User user = (User) session.getAttribute("user");
  if (user == null) {
    response.sendRedirect("loginPage.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile - MegaCityCab</title>
  <link rel="stylesheet" href="profile.css">
</head>
<body>
<div class="profile-container">
  <div class="profile-card">
    <div class="profile-header">
      <h2>My Profile</h2>
    </div>
    <div class="profile-info">
      <div class="profile-image">
        <% if (user.getProfilePicture() != null && !user.getProfilePicture().isEmpty()) { %>
        <img src="<%= user.getProfilePicture() %>" alt="Profile Picture">
        <% } else { %>
        <img src="default-profile.png" alt="Default Profile">
        <% } %>
      </div>
      <div class="profile-details">
        <p><strong>Full Name:</strong> <%= user.getFullName() %></p>
        <p><strong>Username:</strong> <%= user.getUsername() %></p>
        <p><strong>Email:</strong> <%= user.getEmail() %></p>
        <p><strong>Phone:</strong> <%= user.getPhone() %></p>
        <p><strong>Role:</strong> <%= user.getRole() %></p>
      </div>
    </div>
    <div class="profile-actions">
      <a href="mainPage.jsp" class="btn logout">Logout</a>
    </div>
  </div>
</div>
</body>
</html>
