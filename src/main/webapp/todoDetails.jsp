<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo Details</title>
</head>
<body>
    <h2>Todo Details</h2>

    <!-- Accessing todoDescription using EL -->
    <c:choose>
        <c:when test="${not empty todoDescription}">
            <p>Description: ${todoDescription}</p>
        </c:when>
        <c:otherwise>
            <p>No todo found with the given title.</p>
        </c:otherwise>
    </c:choose>

    <a href="index.jsp">Back to Home</a>
</body>
</html>
