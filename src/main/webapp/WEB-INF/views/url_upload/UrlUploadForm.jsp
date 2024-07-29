<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Link</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #e9f7ff;
        }
        .container {
            max-width: 600px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
            position: relative;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .btn {
            transition: transform 0.2s;
        }
        .btn:hover {
            transform: scale(1.1);
        }
        .popup {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            align-items: center;
            justify-content: center;
            z-index: 1000;
        }

        .popup-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .popup-content p {
            font-size: 18px;
            color: #28a745;
        }
        .form-control1 {
            width: 560px;
            border-color: #ced4da;
            border-width: 1px;
            border-radius: 5px;
            height: 40px;
        }
    </style>
    <script>
          document.addEventListener('DOMContentLoaded', function() {
                      var keywordsInput = document.getElementById('keyword');

                      keywordsInput.addEventListener('keypress', function(event) {
                          var char = String.fromCharCode(event.which);

                          // Allow only alphabets and commas
                          if (!/^[a-zA-Z,]$/.test(char)) {
                              event.preventDefault();
                          }
                      });
                  });
        function addKeywordField() {
            var container = document.getElementById("keywordContainer");
            var row = document.createElement("div");
            row.className = "input-group mt-2";

            var input = document.createElement("input");
            input.type = "text";
            input.name = "urlKeywords";
            input.className = "form-control";
            input.required = true;

            var removeButton = document.createElement("button");
            removeButton.className = "btn btn-danger";
            removeButton.type = "button";
            removeButton.innerHTML = "-";
            removeButton.onclick = function() {
                container.removeChild(row);
            };

            var inputGroupAppend = document.createElement("div");
            inputGroupAppend.className = "input-group-append";
            inputGroupAppend.appendChild(removeButton);

            row.appendChild(input);
            row.appendChild(inputGroupAppend);

            container.appendChild(row);
        }

        function showPopup() {
            var popup = document.getElementById("success-popup");
            popup.style.display = "flex";
            document.body.addEventListener("click", function() {
                popup.style.display = "none"; // Hide popup on any click
                window.location.href = "/SearchEngine/upload"; // Redirect to upload page
            }, { once: true });
        }

        document.getElementById("go-back").addEventListener("click", () => {
            history.back();
        });

        // Check if uploadSuccess attribute is present to trigger popup
        <c:if test="${not empty uploadSuccess}">
            showPopup();
        </c:if>
    </script>

</head>
<body>
    <div class="container">
        <h2 class="text-center text-primary mb-4">Add Link</h2>
        <form:form modelAttribute="link" action="upload" method="post">

            <div class="form-group">
                <form:label path="urlTitle">URL Title:</form:label>
                <form:input path="urlTitle" cssClass="form-control" required="true" />
            </div>

            <div class="form-group">
                <form:label path="url">URL:</form:label>
                <form:input path="url" cssClass="form-control" required="true" />
            </div>

            <div class="form-group">
                <form:label path="urlDescription">URL Description:</form:label>
                <form:textarea path="urlDescription" cssClass="form-control" required="true" />
            </div>

            <div class="form-group">
             <label>keywords:</label>
             <br>
             <input type="text" id="keyword" name="keyword" class="form-control1" required="true" />
            </div>
           <br/>

            <input type="submit" value="Save" class="btn btn-primary" />
            <input type="reset" style="margin-left:7px" value="Reset" class="btn btn-secondary"/>
            <a id="go-back" class="btn btn-danger ml-2">Cancel</a>
            <a id="go-home" class="btn btn-success ml-2">Home</a>

        </form:form>
    </div>

    <div id="success-popup" class="popup">
        <div class="popup-content">
            <p>Data uploaded successfully!</p>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        document.getElementById("go-back").addEventListener("click", () => {
          history.back();
        });

        document.getElementById("go-home").addEventListener("click", () => {
          window.location.href='/SearchEngine/';
        });

        <c:if test="${not empty uploadSuccess}">
            showPopup();
        </c:if>
    </script>
</body>
</html>
