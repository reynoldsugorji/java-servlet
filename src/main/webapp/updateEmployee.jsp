<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Employee</title>
</head>
<body>
    <h2>Update Employee</h2>
    <form action="employee?action=update" method='post' id="form">
        <input type="hidden" name="id" value="${employee.id}">

        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" id="firstName" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" id="lastName" required><br>

        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required><br>

        <label for="password">Password:</label>
            <input type="password" name="password" id="password"><br>

        <label for="address">Address:</label>
        <input type="text" name="address" id="address"><br>

        <label for="contact">Contact:</label>
        <input type="text" name="contact" id="contact"><br>

        <input type="submit" value="Update Employee">
    </form>
    <br>
    <a href="employeeDetails.jsp">Go back</a>
</body>
</html>


