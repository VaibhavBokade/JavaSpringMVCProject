<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <title>Edit Task</title>--%>
<%--    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>--%>

<%--    <style>--%>
<%--        body {--%>
<%--            font-family: 'Arial', sans-serif;--%>
<%--            margin: 0;--%>
<%--            padding: 0;--%>
<%--            background-image: url("https://img.freepik.com/free-vector/appointment-booking-with-smartphone-woman_23-2148559138.jpg");--%>
<%--            background-size: cover;--%>
<%--            background-position: center;--%>
<%--            background-repeat: no-repeat;--%>
<%--            display: flex;--%>
<%--            justify-content: center;--%>
<%--            align-items: center;--%>
<%--            height: 100vh;--%>
<%--        }--%>

<%--        h1 {--%>
<%--            text-align: center;--%>
<%--            color: #333333;--%>
<%--            margin-bottom: 20px;--%>
<%--        }--%>

<%--        form {--%>
<%--            width: 40%;--%>
<%--            max-width: 400px;--%>
<%--            background-color: rgba(255, 255, 255, 0.8);--%>
<%--            padding: 20px;--%>
<%--            border-radius: 8px;--%>
<%--            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);--%>
<%--        }--%>

<%--        label {--%>
<%--            display: block;--%>
<%--            margin-bottom: 8px;--%>
<%--            font-weight: bold;--%>
<%--            color: #495057;--%>
<%--        }--%>

<%--        input {--%>
<%--            width: 100%;--%>
<%--            padding: 10px;--%>
<%--            margin-bottom: 20px;--%>
<%--            box-sizing: border-box;--%>
<%--            border: 1px solid #ced4da;--%>
<%--            border-radius: 4px;--%>
<%--        }--%>

<%--        input[type="date"] {--%>
<%--            padding: 8px;--%>
<%--        }--%>

<%--        button {--%>
<%--            background-color: #28a745;--%>
<%--            color: #fff;--%>
<%--            padding: 10px 20px;--%>
<%--            border: none;--%>
<%--            border-radius: 4px;--%>
<%--            cursor: pointer;--%>
<%--            width: 100%;--%>
<%--        }--%>

<%--        button:hover {--%>
<%--            background-color: #218838;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Edit Task</h1>--%>
<%--<form id="editTaskForm">--%>
<%--    <label for="title">Task Title:</label>--%>
<%--    <input type="text" id="title" name="title" placeholder="Enter Task Title" required>--%>

<%--    <label for="description">Description:</label>--%>
<%--    <input type="text" id="description" name="description" placeholder="Enter Task Description" required>--%>

<%--    <label for="priority">Priority:</label>--%>
<%--    <input type="text" id="priority" name="priority" placeholder="Enter Task Priority" required>--%>

<%--    <label for="dueDate">Due Date:</label>--%>
<%--    <input type="date" id="dueDate" name="dueDate" placeholder="Enter Due Date" required>--%>

<%--    <label for="status">Status:</label>--%>
<%--    <input type="text" id="status" name="status" placeholder="Enter status" required>--%>

<%--    <button type="button" onclick="editTask()">Submit</button>--%>
<%--</form>--%>
<%--<script src="<c:url value="/resource/support/js/appjs/editTask.js"/>"></script>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Task</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-image: url("https://img.freepik.com/free-vector/appointment-booking-with-smartphone-woman_23-2148559138.jpg");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #333333;
            margin-bottom: 20px;
        }

        form {
            width: 40%;
            max-width: 400px;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #495057;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #ced4da;
            border-radius: 4px;
        }

        input[type="date"] {
            padding: 8px;
        }

        button {
            background-color: #28a745;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<h1>Edit Task</h1>
<form id="editTaskForm">
    <label for="title">Task Title:</label>
    <input type="text" id="title" name="title" placeholder="Enter Task Title" required value="<c:out value='${task.title}'/>">

    <label for="description">Description:</label>
    <input type="text" id="description" name="description" placeholder="Enter Task Description" required value="<c:out value='${task.description}'/>">

    <label for="priority">Priority:</label>
    <input type="text" id="priority" name="priority" placeholder="Enter Task Priority" required value="<c:out value='${task.priority}'/>">

    <label for="dueDate">Due Date:</label>
    <input type="date" id="dueDate" name="dueDate" placeholder="Enter Due Date" required value="<c:out value='${task.dueDate}'/>">

    <label for="status">Status:</label>
    <input type="text" id="status" name="status" placeholder="Enter status" required value="<c:out value='${task.status}'/>">

    <button type="button" onclick="editTask()">Submit</button>
</form>
<script src="<c:url value="/resource/support/js/appjs/editTask.js"/>"></script>
</body>
</html>
