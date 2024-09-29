<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h2>Employee List</h2>
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Address</th>
                <th>Contact</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${listEmployee}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.username}</td>
                    <td>${employee.address}</td>
                    <td>${employee.contact}</td>
                    <td>
                        <a href="edit?id=${employee.id}">Edit</a> |
                        <a href="delete?id=${employee.id}" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br/>
    <a href="registerEmployee.jsp">Add New Employee</a>
</body>
</html>
