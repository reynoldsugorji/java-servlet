<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>File Management</title>
</head>
<body>
    <h2>File Upload</h2>

    <form method="post" action="upload" enctype="multipart/form-data">
        <label for="file">Upload a file:</label>
        <input type="file" name="file" id="file">
        <input type="submit" value="Upload">
    </form>

    <%
        // Display the success message
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<p style='color: green;'>" + message + "</p>");
        }
    %>
</body>
</html>
