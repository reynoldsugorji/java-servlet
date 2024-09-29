<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo Management</title>
</head>
<body>
    <h2>Add Todo</h2>
    <form action="todo" method="post">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required><br><br>
        <input type="submit" value="Add Todo">
    </form>
    <h2>Search Todo</h2>
    <form action="todo" method="get">
        <label for="searchTitle">Title:</label>
        <input type="text" id="searchTitle" name="searchTitle" required><br><br>
        <input type="submit" value="Search Todo">
    </form>
</body>
</html>
