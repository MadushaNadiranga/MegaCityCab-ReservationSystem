<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<div class="sidebar">
    <h2>MegaCityCab</h2>
    <ul>
        <li><a href="addUsers.jsp">ADD USERS</a></li>
        <li><a href="addDriver.jsp">ADD DRIVERS</a></li>
        <li><a href="addVehicle.jsp">ADD VEHICLES</a></li>
        <li><a href="#">ADD BOOKINGS</a></li>
        <li><a href="manageUsers.jsp">MANAGE USERS</a></li>
        <li><a href="#">MANAGE BOOKINGS</a></li>
        <li><a href="#">MANAGE DRIVERS</a></li>
        <li><a href="#">MANAGE VEHICLES</a></li>
        <li><a href="#">MANAGE CUSTOMERS</a></li>
        <li><a href="#">REPORTS</a></li>

    </ul>
        <div class="logout">
            <a href="mainPage.jsp">Logout</a>
        </div>
</div>

<div class="main-content">
    <header>
        <h1>DASHBOARD</h1>
    </header>

    <div class="stats">
        <div class="card">
            <h3>Total Income</h3>
            <p>Rs.1,112,784</p>
        </div>
        <div class="card">
            <h3>Total Bookings</h3>
            <p>28,834</p>
        </div>
        <div class="card">
            <h3>Refunded</h3>
            <p>2,876</p>
        </div>
        <div class="card">
            <h3>Customers</h3>
            <p>18,896</p>
        </div>
    </div>

    <div class="charts-container">
        <!-- Bar Chart for Total Bookings -->
        <div class="chart-box">
            <h3>Total Bookings per Month</h3>
            <canvas id="totalBookingsChart"></canvas>
        </div>

        <!-- Pie Chart for Customer Distribution -->
        <div class="chart-box">
            <h3>Bookings</h3>
            <canvas id="customerChart"></canvas>
        </div>
    </div>

</div>
<script>
    // Total Bookings Bar Chart Data
    const bookingsCtx = document.getElementById('totalBookingsChart').getContext('2d');
    new Chart(bookingsCtx, {
        type: 'bar',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            datasets: [{
                label: 'Total Bookings',
                data: [120, 150, 180, 200, 220, 250, 300, 320, 280, 260, 230, 210], // Example data
                backgroundColor: 'rgba(75, 192, 192, 0.6)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: { beginAtZero: true }
            }
        }
    });

    // Customer Distribution Pie Chart Data
    const customerCtx = document.getElementById('customerChart').getContext('2d');
    new Chart(customerCtx, {
        type: 'pie',
        data: {
            labels: ['Last Year', 'This Year'],
            datasets: [{
                label: 'Customer Distribution',
                data: [65, 35], // Example data: 65% new customers, 35% returning
                backgroundColor: ['#36A2EB', '#FF6384']
            }]
        },
        options: {
            responsive: true,
        }

    });
</script>

</body>
</html>
