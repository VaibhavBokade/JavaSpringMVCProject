<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: url("https://img.lovepik.com/element/45009/2241.png_860.png");
            background-size: cover;
            background-position: top;
        }

        form {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 50%;
            max-width: 400px;
            text-align: center;
        }

        h2 {
            color: #dc3545;
            margin-bottom: 30px;
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
<form action="addTask" method="post">
    <h2>Add Task</h2>

    <label for="title">Task Title:</label>
    <input type="text" id="title" name="title" placeholder="Enter task title" required pattern="[A-Za-z\s]+"
           title="Only letters and spaces are allowed" maxlength="20">

    <label for="description">Task Description:</label>
    <input type="text" id="description" name="description" placeholder="Enter task description" required>

    <label for="dueDate">Due Date:</label>
    <input type="date" id="dueDate" name="dueDate" placeholder="Select due date"
           required pattern="\d{4}-\d{2}-\d{2}">

    <label for="priority">Priority:</label>
    <input type="number" id="priority" name="priority" placeholder="Enter task priority" required>

    <button type="button" onclick="addTask()">Add Task</button>
</form>
<script src="<c:url value="/resource/support/js/appjs/addTask.js"/>"></script>
</body>

</html>
