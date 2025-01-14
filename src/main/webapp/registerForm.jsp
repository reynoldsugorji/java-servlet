<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Employee</title>
</head>
<body>
    <h2>Create New Employee</h2>
    <form method="post" action="employee?action=create">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" id="firstName" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" id="lastName" required><br>

        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required><br>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required><br>

        <label for="address">Address:</label>
        <input type="text" name="address" id="address"><br>

        <label for="contact">Contact:</label>
        <input type="text" name="contact" id="contact"><br>

        <input type="submit" value="Create Employee">
    </form>
    <br>
    <a href="employee?action=list">View Employee List</a>
</body>
</html>
