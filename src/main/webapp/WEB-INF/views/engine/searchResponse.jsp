<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            width: 90%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border: none;
            border-radius: 25px;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .search-bar {
            position: relative;
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        .search-bar input[type="text"] {
            flex: 1;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
        }
        .search-bar button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4285f4;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .search-bar button:hover {
            background-color: #357ae8;
        }
        .result {
            border-bottom: 1px solid #e0e0e0;
            padding: 30px 0;
        }
        .result:last-child {
            border-bottom: none;
        }
        .result-title {
            font-size: 18px;
            font-weight: bold;
            color: #1a0dab;
        }
        .result-title a {
            text-decoration: none;
            color: inherit;
        }
        .result-title a:hover {
            text-decoration: underline;
        }
        .result-url {
            font-size: 14px;
            color: #006621;
            margin: 5px 0;
        }
        .result-description {
            font-size: 14px;
            color: #545454;
        }
        .no-results {
            text-align: center;
            color: #999;
            font-size: 18px;
        }
        .no-results img {
            width: 100%;
            max-width: 600px;
            height: auto;
            margin-top: 2px;
        }

        #suggestions {
            list-style: none;
            padding: 0;
            margin: 0;
            position: absolute;
            top: calc(100% + 5px); /* Adjust top position to be just below the input */
            left: 0; /* Align to the left of the input */
            width: 100%; /* Match the width of the input */
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            z-index: 1000;
            display: none;
        }

        #suggestions li {
            padding: 10px;
            cursor: pointer;
        }
        #suggestions li:hover {
            background-color: #f0f0f0;
        }
        #suggestions.active {
            display: block;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#search-input').on('keyup', function(event) {
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
                                        window.location.href = '' + encodeURIComponent(searchKeyword);
                                    }
                                    $('#search-input').val(searchKeyword);
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
                let keyword = $('#search-input').val().trim();
                if (keyword.length > 0) {
                    window.location.href = '' + encodeURIComponent(keyword);
                } else {
                    alert('Please enter a keyword to search.');
                }
            });

            // Trigger search on Enter key press
            $('#search-input').on('keydown', function(event) {
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

        });
    </script>
</head>
<body>
    <div class="container">
        <div class="search-bar">
            <a href="/SearchEngine/search" style="margin-right: 20px; text-decoration: none; color: inherit;">
                <h1>Nucleus Search</h1>
            </a>
            <input type="text" id="search-input" name="keyword" value="${searchQuery}" />
            <button id="searchBtn" type="button" onclick="search()">Search</button>
            <ul id="suggestions"></ul>
        </div>
        <hr>
        <c:choose>
            <c:when test="${empty fetchedResults && empty fetchedFileResults}">
                <div class="no-results">
                    <p style="font-size:24px; color:red;"><b>No Result Found !!!</b></p>
                    <img src="${pageContext.request.contextPath}/static/notFoundPage.jpg" alt="Not Found Page">
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="link" items="${fetchedResults}">
                    <div class="result">
                        <div class="result-title">
                            <a href="${link.url}" target="_blank">${link.urlTitle}</a>
                        </div>
                        <div class="result-url">${link.url}</div>
                        <div class="result-description">${link.urlDescription}</div>
                    </div>
                </c:forEach>
                <c:forEach var="pdf" items="${fetchedFileResults}">
                    <div class="result">
                        <div class="result-title">
                            <a href="getPdf?id=${pdf.id}" target="_blank">${pdf.fileTitle}</a>
                        </div>
                        <div class="result-url">${pdf.fileTitle}</div>
                        <div class="result-description">${pdf.fileDescription}</div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
