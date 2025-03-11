<%@ page import="com.reservation.dao.UserDAO, com.reservation.model.User, java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" type="text/css" href="manageUsers.css">
</head>
<body>
<div class="container">
    <h2>User Management</h2>

    <%
        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.getAllUsers();
    %>

    <h3>Users List</h3>
    <div class="user-container">
        <%
            if (users == null || users.isEmpty()) {
        %>
        <p class="error">No users found.</p>
        <%
        } else {
            for (User user : users) {
        %>
        <div class="user-card">
            <%-- Debugging: Print user data directly --%>
            <p>Username: <%= user.getUsername() %></p>
            <p>Email: <%= user.getEmail() %></p>
            <img src="<%= user.getProfilePicture() %>" alt="Profile Picture" class="profile-pic">

            <div class="user-info">
                <h4><%= user.getFullName() %></h4>
                <p><strong>Phone:</strong> <%= user.getPhone() %></p>
                <p><strong>Role:</strong> <%= user.getRole() %></p>
                <form action="userManagement" method="post">
                    <input type="hidden" name="username" value="<%= user.getUsername() %>">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit" class="delete-btn">Delete</button>
                </form>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>

</div>
</body>
</html>