<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h2>Employee List</h2>

    <c:if test="${not empty employees}">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Address</th>
                <th>Contact</th>
                <th>Actions</th>
            </tr>

            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.username}</td>
                    <td>${employee.address}</td>
                    <td>${employee.contact}</td>
                    <td>
                        <a href="employee?action=details&id=${employee.id}">View</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty employees}">
        <p>No employees found.</p>
    </c:if>
</body>
</html>
