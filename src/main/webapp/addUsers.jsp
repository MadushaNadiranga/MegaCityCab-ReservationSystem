<!DOCTYPE html>
<html>
<head>
    <title>Add New User</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<div class="container">
    <h2>Add New User</h2>

    <%-- Success or error messages --%>
    <% if (request.getParameter("success") != null) { %>
    <p class="success">User added successfully!</p>
    <% } else if (request.getParameter("error") != null) { %>
    <p class="error">Error adding user. Please try again.</p>
    <% } %>

    <form action="UserServlet" method="post" enctype="multipart/form-data">
        <label>Full Name:</label>
        <input type="text" name="full_name" required>

        <label>Username:</label>
        <input type="text" name="username" required>

        <label>Email:</label>
        <input type="email" name="email" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <label>Phone:</label>
        <input type="text" name="phone">

        <label>Role:</label>
        <select name="role" required>
            <option value="admin">Admin</option>
            <option value="manager">Manager</option>
            <option value="employee">Employee</option>
        </select>

        <label>Profile Picture:</label>
        <input type="file" name="profile_picture" required>

        <input type="submit" value="Add User">
    </form>
</div>

</body>
</html>
