<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - MegaCityCab</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="login-container">
    <!-- Left Side - Login Form -->
    <div class="login-card">
        <h2>Login</h2>
        <form action="dashboard.html" method="POST">
            <div class="input-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="login-btn">Login</button>
        </form>
    </div>

    <!-- Right Side - Background Image -->
    <div class="login-image"></div>
</div>
</body>
</html>
