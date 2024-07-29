<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pdfTitle}</title>
    <style>
        #pdf-viewer {
            border: 1px solid #ccc;
            margin: 20px auto;
            max-width: 1000px;
        }
    </style>
</head>
<body>
    <div id="pdf-viewer"></div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.10.377/pdf.min.js"></script>

    <script>
        // Function to convert Java byte array to JavaScript Uint8Array
        function convertByteArrayToUint8Array(byteArray) {
            var uint8Array = new Uint8Array(byteArray.length);
            for (var i = 0; i < byteArray.length; i++) {
                uint8Array[i] = byteArray[i];
            }
            return uint8Array;
        }

        // Retrieve the PDF data from backend as byte[]
        var pdfData = convertByteArrayToUint8Array([
            <c:forEach items="${pdfBytes}" var="byt">${byt},</c:forEach>
        ]);

        // Load PDF document
        pdfjsLib.getDocument({ data: pdfData }).promise.then(function(pdfDoc) {
            // Render the first page of PDF
            var numPages = pdfDoc.numPages;
            var pdfViewer = document.getElementById('pdf-viewer');

            console.log(numPages);

            for( var pageNum = 1; pageNum<= numPages; pageNum++){

            pdfDoc.getPage(pageNum).then(function(page) {
                var canvas = document.createElement('canvas');
                var context = canvas.getContext('2d');
                var viewport = page.getViewport({ scale: 1.5 });

                canvas.height = viewport.height;
                canvas.width = viewport.width;

                var renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };

                page.render(renderContext);
                pdfViewer.appendChild(canvas);
            });

            }

        });

        // Prevent right-click context menu
        document.addEventListener('contextmenu', function(event) {
            event.preventDefault();
        });

        // Prevent key press actions
        document.addEventListener('keydown', function(event) {
            event.preventDefault();
        });
    </script>
</body>
</html>
