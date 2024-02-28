<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Display Tasks</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f0f0f0;
        }

        h1 {
            text-align: center;
            color: #333333;
        }

        table {
            width: 70%;
            margin-left: 15%;
            margin-right: 15%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<h1>Display Completed Tasks Assigned to You</h1>
<table>
    <thead>
    <tr>
        <th>Sr. No.</th>
        <th>Task Title</th>
        <th>Description</th>
        <th>Due Date</th>
        <th>Priority</th>
    </tr>
    </thead>
    <tbody id="tasks">

    </tbody>
</table>
<script src="<c:url value="/resource/support/js/appjs/displayTotalCompletedTasks.js"/>"></script>
</body>
</html>
