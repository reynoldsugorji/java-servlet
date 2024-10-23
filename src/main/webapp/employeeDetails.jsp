<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Details</title>
</head>
<body>
    <h2>Employee Details</h2>

    <c:choose>
        <c:when test="${not empty employee}">
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>ID</th>
                    <td>${employee.id}</td>
                </tr>
                <tr>
                    <th>First Name</th>
                    <td>${employee.firstName}</td>
                </tr>
                <tr>
                    <th>Last Name</th>
                    <td>${employee.lastName}</td>
                </tr>
                <tr>
                    <th>Username</th>
                    <td>${employee.username}</td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td>${employee.address}</td>
                </tr>
                <tr>
                    <th>Contact</th>
                    <td>${employee.contact}</td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <p>${errorMessage}</p>
        </c:otherwise>
    </c:choose>

    <br/>
    <a href="employee?action=list">Back to Employee List</a>
     <a href="employee?action=edit&id=${employee.id}">Update</a>
     <a href="employee?action=delete&id=${employee.id}">Delete</a>
</body>
</html>
