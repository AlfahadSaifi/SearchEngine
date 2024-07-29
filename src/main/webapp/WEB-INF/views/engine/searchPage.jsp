<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <title>Nucleus Search</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #6f42c1, #007bff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #fff;
        }

        .wrapper {
            text-align: center;
        }

        .main {
            text-align: center;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            color: #333;
            width: 200%;
        }

        .search-box {
            position: relative;
            width: 100%;
            max-width:1500px;
            margin: 20px auto;
            margin-right: 12px;
        }

        .search-box input[type="text"] {
            width: 100%;
            padding: 15px 15px 15px 50px; /* Adjusted padding for icon */
            font-size: 18px;
            border: 1px solid #dfe1e5;
            border-radius: 50px;
            outline: none;
            box-shadow: 0 1px 6px rgba(32, 33, 36, 0.28);
            transition: box-shadow 0.3s;
        }

        .search-box input[type="text"]:focus {
            box-shadow: 0 4px 8px rgba(32, 33, 36, 0.28);
        }

        .search-icon {
            position: absolute;
            top: 50%;
            left: 15px;
            transform: translateY(-50%);
            font-size: 18px;
            color: #a0a0a0;
        }

        .search-btn {
            position: relative;
            width: 25%;
            max-width:1500px;
            margin: 20px auto;
            font-size: 18px;
            border: none;
            border-radius: 50px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
        }

        .search-btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        #cont{
            display : flex;
            flex-direction: row;
        }

        #suggestions {
            list-style: none;
            margin: 0;
            padding: 0;
            border: 1px solid #dfe1e5;
            border-top: none;
            max-height: 150px;
            overflow-y: auto;
            box-shadow: 0 4px 6px rgba(32, 33, 36, 0.28);
            background-color: #fff;
            border-radius: 24px 24px 24px 24px;
            width: 100%;
            z-index: 1000;
            display: none; /* Hide initially */
            position: absolute;
        }

        #suggestions.active {
            display: block; /* Show when active */
        }

        #suggestions li {
            padding: 10px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        #suggestions li:hover {
            background-color: #f1f1f1;
        }

        .wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 40%;
            height: auto; /* Optional: Set height to center vertically */
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
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#searchBox').on('keyup', function(event) {
                let keyword = $(this).val().trim();
                console.log("Keyword: " + keyword); // Debugging statement
                if (keyword.length > 0) {
                    $.ajax({
                        url: '/SearchEngine/search/suggestions',
                        type: 'GET',
                        data: { keyword: keyword },
                        success: function(response) {
                            console.log("Response: ", response);
                            let suggestionsList = $('#suggestions');
                            suggestionsList.empty();
                            $.each(response, function(index, keyword) {
                                let li = $('<li></li>').text(keyword);
                                li.on('click', function() {
                                    let searchKeyword = $(this).text();
                                    if (searchKeyword) {
                                        window.location.href = 'search/response/' + encodeURIComponent(searchKeyword);
                                    }
                                    $('#searchBox').val(searchKeyword);
                                    suggestionsList.removeClass('active');
                                });
                                suggestionsList.append(li);
                            });
                            suggestionsList.addClass('active'); // Show suggestions
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error("Error: ", textStatus, errorThrown);
                        }
                    });
                } else {
                    $('#suggestions').empty().removeClass('active'); // Hide suggestions
                }
            });

            $('#searchBtn').on('click', function() {
                let keyword = $('#searchBox').val().trim();
                if (keyword.length > 0) {
                    window.location.href = 'search/response/' + encodeURIComponent(keyword);
                } else {
                    alert('Please enter a keyword to search.');
                }
            });

            // Trigger search on Enter key press
            $('#searchBox').on('keydown', function(event) {
                if (event.key === 'Enter') {
                    event.preventDefault(); // Prevent form submission
                    $('#searchBtn').click(); // Trigger search button click
                }
            });

            // Close suggestions on outside click
            $(document).on('click', function(e) {
                if (!$(e.target).closest('#suggestions').length) {
                    $('#suggestions').removeClass('active');
                }
            });

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
        });
    </script>
</head>
<body>
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

        <!-- Home and Logout Links -->
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

<div class="wrapper">
    <div class="main">
        <a href="${pageContext.request.contextPath}/">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqfy_ymD9F10Vd4KJStiezjapwwKwGjjmjvg&s" style="mix-blend-mode: multiply">
        </a>
        <div id="cont">
            <div class="search-box">
                <i class="fas fa-search search-icon"></i>
                <input type="text" id="searchBox" autocomplete="off">
                <ul id="suggestions"></ul>
            </div>
            <button id="searchBtn" class="search-btn">Search</button>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script> <!-- For Font Awesome Icons -->
</body>
</html>
