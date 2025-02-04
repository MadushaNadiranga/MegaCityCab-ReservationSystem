<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="styles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
<div class="dashboard-container"> <!-- Add this wrapper -->
    <div class="sidebar">
        <h2>MegaCityCab</h2>
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Bookings</a></li>
            <li><a href="#">Manage Employee</a></li>
            <li><a href="#">Manage Drivers</a></li>
            <li><a href="#">Manage Vehicles</a></li>
            <li><a href="#">Help</a></li>
        </ul>
        <div class="logout">
            <a href="logout.jsp">Logout</a>
        </div>
    </div>

    <div class="main-content">
        <header>
            <h1>Dashboard</h1>
        </header>

        <div class="stats">
            <div class="card">
                <h3>Total Bookings</h3>
                <p>12,784</p>
            </div>
            <div class="card">
                <h3>Our Customer</h3>
                <p>28,834</p>
            </div>
            <div class="card">
                <h3>Total Drivers</h3>
                <p>1,500</p>
            </div>
            <div class="card">
                <h3>Total Vehicles</h3>
                <p>2,000</p>
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
                <h3>Our Customers</h3>
                <canvas id="customerChart"></canvas>
            </div>
        </div>

    </div>
</div> <!-- End wrapper -->

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
            labels: ['THis Year', 'Last Year'],
            datasets: [{
                label: 'Customer Distribution',
                data: [65, 35], // Example data: 65% new customers, 35% returning
                backgroundColor: ['#36A2EB', '#FF6384']
            }]
        },
        options: {
            responsive: true
        }
    });
</script>


</body>

</html>
