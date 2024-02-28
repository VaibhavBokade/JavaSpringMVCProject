<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <title>Task Manager</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
            background-color: #ecf0f1;
            padding: 10px;
            width: 100%;
            margin-bottom: 20px;
        }

        .dashboard-container {
            background-color: #ecf0f1;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 60%;
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
        }

        .button-container {
            margin-top: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            width: 100%;
        }

        .dashboard-container button {
            background: #3498db;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin: 5px;
            transition: background 0.3s ease;
        }

        .dashboard-container button:hover {
            background: #2980b9;
        }

        #logoutBtn {
            background: #e67e22;
        }

        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: white;
            padding: 20px;
            border: 1px solid #888888;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            z-index: 1;
        }

        #getUserDetailsBtn {
            padding: 10px;
            cursor: pointer;
        }

        h1 {
            color: #27ae60;
            margin-bottom: 10px;
        }

        #closePopup {
            padding: 8px;
            background-color: #2ecc71;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        .slider {
            overflow: hidden;
            width: 100vw;
            height: 50vh;
            position: relative;
            margin-bottom: 20px;
            border-radius: 8px;
        }

        .slider .slide {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-size: cover;
            background-position: center;
            animation: slider 12.5s infinite;
        }

        .slider .slide:nth-child(1) {
            background-image: url('https://images.unsplash.com/photo-1553034545-32d4cd2168f1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fHRhc2slMjBtYW5hZ2VtZW50fGVufDB8fDB8fHww');
            animation-delay: 0s;
        }
        .slider .slide:nth-child(2) {
            background-image: url('https://images.unsplash.com/photo-1590402494756-10c265b9d736?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8dGFzayUyMG1hbmFnZW1lbnR8ZW58MHx8MHx8fDA%3D');
            animation-delay: -2.5s;
        }

        .slider .slide:nth-child(3) {
            background-image: url('https://media.istockphoto.com/id/1329061586/photo/woman-hands-holding-a-time-blocking-weekly-calendar-to-organize.webp?b=1&s=170667a&w=0&k=20&c=woWti7fFZ8kt2vJUz_rSps_LNoqU_30VRHWO8M3aZi8=');
            animation-delay: -5s;
        }

        .slider .slide:nth-child(4) {
            background-image: url('https://images.unsplash.com/photo-1517430554953-a5ba4678fe85?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80');
            animation-delay: -7.5s;
        }

        .slider .slide:nth-child(5) {
            background-image: url('https://media.istockphoto.com/id/1385564571/photo/home-office-features.webp?b=1&s=170667a&w=0&k=20&c=rnERdnmbjvm0UjI9jDbGUD6JUM9uGLLqTMb8VEXmkVE=');
            animation-delay: -10s;
        }

        @keyframes slider {
            0%, 16%, 100% {
                transform: translateX(0);
                animation-timing-function: ease;
            }
            20% {
                transform: translateX(-100%);
                animation-timing-function: step-end;
            }
            96% {
                transform: translateX(100%);
                animation-timing-function: ease;
            }
        }
    </style>
</head>

<body>
<h1>User Dashboard</h1>

<div class="dashboard-container">
    <div id="myPopup" class="popup">
        <table id="userTable">
            <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
        <button id="closePopup">Close</button>
    </div>
    <div class="button-container">
        <button type="button" id="getUserDetailsBtn">My Details</button>
        <button type="button" id="addTaskBtn" onclick="redirectToTaskPage()">Add Task</button>
        <button type="button" id="showTasks" onclick="displayTaskPage()">Display Tasks</button>
        <button type="button" id="removeTaskBtn" onclick="redirectToRemovePage()">Remove Completed Task</button>
        <button type="button" id="displayCompletedTaskBtn" onclick="redirectToDisplayPage()">Display Completed Task</button>
<%--        <button type="button" id="editTaskBtn" onclick="redirectToEditTaskPage()">Edit Task</button>--%>
        <button type="button" id="logoutBtn">Logout</button>
    </div>
    <div class="slider">
        <div class="slide"></div>
        <div class="slide"></div>
        <div class="slide"></div>
        <div class="slide"></div>
        <div class="slide"></div>
    </div>
</div>

<script src="<c:url value="/resource/support/js/appjs/dashboard.js"/>"></script>
</body>

</html>
