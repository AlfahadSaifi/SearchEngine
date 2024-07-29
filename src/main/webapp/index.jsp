<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nucleus Search Engine</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f5f5f5;
            padding-top: 70px; /* Adjust padding to avoid content overlapping with the navbar */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .navbar {
            width: 100%;
            background-color: #F8F9FA; /* Light Gray */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            font-size: 120%;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
            height: 80px; /* Adjust the height of the navbar */
        }
        .navbar-brand {
            color: #007BFF; /* Blue */
            display: flex;
            align-items: center;
            font-weight: bold;
        }
        .navbar-brand img {
            margin-right: 10px;
        }
        .navbar-text-movable {
            color: #333; /* Dark gray */
            font-size: 100%;
            width: 100%;
            display: inline-block;
            white-space: nowrap;
            animation: slideRight 10s linear infinite;
        }
        .navbar-text {
            color: #333; /* Dark gray */
            font-size: 100%;
            display: inline-block;
        }
        .navbar-text a {
            color: #007BFF; /* Blue */
            font-weight: bold;
            text-decoration: none;
        }
        .navbar-text a:hover {
            color: #0056B3; /* Dark blue */
        }
        .username-wrapper {
            width: 60%;
            display: flex;
            align-items: center;
            overflow: hidden;
        }
        @keyframes slideRight {
            0% { transform: translateX(-100%); }
            100% { transform: translateX(100%); }
        }
        .username-wrapper span {
            display: inline-block;
            vertical-align: middle;
            margin-right: 10px;
        }
        .navbar-text.home {
            margin-right: 10px; /* Adjust the margin as needed */
        }
        .navbar-text.logout {
            margin-left: 10px; /* Adjust the margin as needed */
            margin-right: 10px;
        }
        #current-time {
            margin-left: 10px;
            display: inline !important; /* Ensure it stays inline */
            color: green;
            font-weight: bolder;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: calc(100vh - 70px); /* Adjust height to account for navbar */
            margin-left:230px;
        }

        .card {
            width: 250px; /* Increased card width */
            height: 200px;
            text-align: center;
            padding-top: 80px; /* Adjust vertical alignment */
            margin: 10px 100px; /* Adjusted margin to add space between cards */
            background-color: #ffffff; /* White background */
            border: none; /* No border */
            border-radius: 10px; /* Rounded corners */
            justify-content: center;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); /* Shadow effect */
            transition: transform 0.3s, box-shadow 0.3s; /* Smooth hover effect */
            padding:25px;
        }

        .card:hover {
            transform: translateY(-10px); /* Lift the card on hover */
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2); /* Stronger shadow on hover */
        }

        .card h5 {
            font-size: 1.5rem; /* Increased title font size */
            font-weight: bold; /* Bold title */
            color: #007BFF; /* Blue color */
            margin-bottom: 20px; /* Space below title */
        }

        .card a {
            text-decoration: none;
            color: #ffffff;
            font-size: 1.2rem; /* Increased button font size */
            padding: 10px 20px; /* Button padding */
            background-color: #007BFF; /* Blue button background */
            border-radius: 5px; /* Rounded button corners */
            transition: background-color 0.3s; /* Smooth hover effect */
        }

        .card a:hover {
            background-color: #0056B3; /* Darker blue on hover */
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container d-flex justify-content-between align-items-center">
        <!-- Logo and Brand Name -->
        <div class="navbar-brand">
            <a href="${pageContext.request.contextPath}/">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqfy_ymD9F10Vd4KJStiezjapwwKwGjjmjvg&s" alt="Logo" height="60" width="130" style="border-radius: 50%;">
            </a>
        </div>

        <!-- Username -->
        <div class="username-wrapper">
            <div class="navbar-text-movable">
                <span><b>Welcome to Nucleus Search Engine</b></span>
            </div>
        </div>

        <!-- Home Link -->
        <div class="navbar-text home">
            <a href="${pageContext.request.contextPath}">Home</a>
        </div>

        <!-- Logout Link-->
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <div class="navbar-text logout">
                <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i>Logout</a>
            </div>
        </form>

        <!-- Current Time -->
        <div class="navbar-text">
            <p id="current-time"></p>
        </div>

    </div>
</nav>

<!-- Main Content -->
<div class="container text-center">
    <div class="row justify-content-center"> <!-- Center aligning the row -->
        <!-- Cards -->
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Search Here</h5>
                    <a href="search" class="btn btn-info stretched-link">Go to Search</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add URL Data</h5>
                    <a href="upload" class="btn btn-info stretched-link">Go to Upload</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Add File Data</h5>
                    <a href="pdf/upload" class="btn btn-info stretched-link">Go to Upload</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Script for Current Date and Time -->
<script>
    function displayCurrentTime() {
        var currentTime = new Date();
        var hours = currentTime.getHours();
        var minutes = currentTime.getMinutes();
        var seconds = currentTime.getSeconds();

        // Add leading zeros if needed
        hours = (hours < 10 ? "0" : "") + hours;
        minutes = (minutes < 10 ? "0" : "") + minutes;
        seconds = (seconds < 10 ? "0" : "") + seconds;

        var timeString = hours + ":" + minutes + ":" + seconds;

        document.getElementById("current-time").innerHTML = timeString;

        // Update time every second
        setTimeout(displayCurrentTime, 1000);
    }

    // Call the function when the page is loaded
    displayCurrentTime();
</script>

</body>
</html>
