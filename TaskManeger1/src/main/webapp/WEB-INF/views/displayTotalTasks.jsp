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
        .buttons-container button {
            background-color: darkorange;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-bottom: 5px;
        }
        .search-container {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 5px;
        }

        .search-bar {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 30%;
        }

        .search-select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 10%;
            margin-right: 5px;
        }

        .search-buttons button {
            background-color: orangered;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 5px;
            width: 10%;
        }
    </style>

</head>
<body>
<h1>Tasks Assigned to You</h1>
<div class="buttons-container">
    <button type="button" id="sortTaskBtn" onclick="redirectToSortByPriorityPage()">Sort by Priority</button>
    <button type="button" id="sortByDueDateTasks" onclick="redirectToSortPage()">Sort by Due date</button>
</div>

<div class="search-container">
    <select class="search-select" id="searchBy">
        <option value="search">Search</option>
        <option value="title">Search by Title</option>
        <option value="dueDate">Search by Due Date</option>
        <option value="status">See status of Task</option>
    </select>
    <input type="text" placeholder="Search..." class="search-bar" id="searchInput">
    <button type="button" id="searchTask" class="search-buttons">
        <img src="https://w7.pngwing.com/pngs/582/430/png-transparent-search-magnifier-find-zoom-glass-seo-optimization-instagram-icon.png" alt="Search Icon" style="width: 20px; height: 20px;">
    </button>
</div>

<table>
    <thead>
    <tr>
        <th>Sr. No.</th>
        <th>Task Title</th>
        <th>Description</th>
        <th>Due Date</th>
        <th>Priority</th>
        <th>Status</th>
        <th>Edit task</th>
        <th>Delete task</th>
    </tr>
    </thead>
    <tbody id="tasks"></tbody>
</table>

<script src="<c:url value="/resource/support/js/appjs/displayTotalTasks.js"/>"></script>
</body>
</html>
