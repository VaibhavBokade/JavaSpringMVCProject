<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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

        label {
            display: block;
            margin-bottom: 10px;
        }

        input {
            padding: 8px;
            margin-bottom: 5px;
            width: 30%;
        }

        button {
            padding: 8px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            width: 10%; /* Reduced width */
            margin-bottom: 5px; /* Reduced margin */
        }

        table {
            width: 80%;
            margin: 20px auto;
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
    <title>Search Tasks by Title</title>
</head>
<body>

<h1>Search Tasks by Title</h1>

<label for="titleInput">Enter Task Title: </label>
<input type="text" id="titleInput">
<button id="searchButton">Search</button>

<table border="1">
    <thead>
    <tr>
        <th>Sr. No.</th>
        <th>Task Title</th>
        <th>Description</th>
        <th>Due Date</th>
        <th>Priority</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody id="tasksTableBody">

    </tbody>
</table>

<script src="<c:url value="/resource/support/js/appjs/searchByTitle.js"/>"></script>

</body>
</html>
