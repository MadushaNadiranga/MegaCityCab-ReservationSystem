<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - MegaCityCab</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
<div class="login-container">
    <!-- Left Side - Login Form -->
    <div class="login-card">
        <h1>Login</h1>
        <p>Smart management for Mega City Cab.<br>Sign in to get started!</p>
        <form action="LoginServlet" method="POST">
            <div class="input-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>

        <% if (request.getParameter("error") != null) { %>
        <div class="error-message">Invalid username or password. Please try again.</div>
        <% } %>
    </div>

    <!-- Right Side - Background Image -->
    <div class="login-image"></div>
</div>
</body>
</html>
