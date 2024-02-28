<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Manager</title>
    <!-- Favicon -->
    <link rel="icon" href="TaskFavicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="TaskFavicon.ico" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-image: url('https://cdn.wedevs.com/uploads/2020/06/Task-Manager-App-.png?f=webp&q=90');
            background-size: cover;
        }

        .marquee-container {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            text-align: center;
            z-index: 1000;
        }

        .marquee {
            font-size: 24px;
            color: #333;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        p {
            color: #666;
        }
        a {
            background-color: orange;
            color: #fff;
            padding: 10px;
            text-decoration: none;
            border-radius: 4px;
            cursor: pointer;
            margin: 5px;
            display: inline-block;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #ff9900;
        }

    </style>
</head>
<body>

<div class="marquee-container">
    <marquee class="marquee" behavior="scroll" direction="left">Task Manager Application : Manage Your Task Efficiently ! : Time to boost your productivity! Your tasks are waiting for you....</marquee>
</div>

<div class="container">
    <h1>Welcome to Task Manager</h1>
    <p>Organize your tasks efficiently.</p>

    <a href="/TaskManager1/login">Login</a>
    <a href="/TaskManager1/register">Register</a>
</div>

</body>
</html>
