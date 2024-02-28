<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        :root {
            --secondary-color: #151226;
            --contrast-color: #BF307F;
        }

        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            height: 100%;
            z-index: -10;
            background-color: var(--contrast-color);
        }

        .container {
            display: flex;
            height: 100vh;
            justify-content: space-around;
            align-items: center;
            color: #fff;
            animation: expand .8s ease forwards;
            background-color: var(--secondary-color);
            position: relative;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            transition: all .8s ease;
        }

        .container_content {
            width: 50%;
        }

        .container_content_inner {
            width: 80%;
            margin-left: 80px;
        }

        .container_outer_img {
            margin: 50px;
            width: 50%;
            overflow: hidden;
        }

        .container_img {
            width: 100%;
            animation: slideIn 1.5s ease-in-out forwards;
        }

        .par {
            height: auto;
            overflow: hidden;
        }

        p {
            line-height: 28px;
            transform: translateY(20px); /* Adjusted translateY value */
            animation: slideUp .8s ease-in-out forwards .8s;
        }

        .btns {
            height: 100%;
            position: relative;
            width: 150px;
            overflow: hidden;
        }

        .btns_more {
            background: transparent;
            border: 1px solid var(--contrast-color);
            border-radius: 50px;
            padding: 8px 12px;
            color: #BF307F;
            font-size: 16px;
            text-transform: uppercase;
            position: relative;
            margin-top: 15px;
            outline: none;
            transform: translateY(20px); /* Adjusted translateY value */
            animation: slideUp .8s ease-in-out forwards 1s;
        }

        .title {
            overflow: hidden;
            height: auto;
        }

        h1 {
            font-size: 40px;
            color: var(--contrast-color);
            margin-bottom: 20px;
            transform: translateY(20px); /* Adjusted translateY value */
            animation: slideUp .8s ease forwards .5s;
        }

        @keyframes slideIn {
            0% {
                transform: translateX(500px) scale(.2);
            }
            100% {
                transform: translateX(0px) scale(1);
            }
        }

        @keyframes slideUp {
            0% {
                transform: translateY(20px); /* Adjusted translateY value */
            }
            100% {
                transform: translateY(0px);
            }
        }

        @keyframes expand {
            0% {
                transform: translateX(1400px);
            }
            100% {
                transform: translateX(0px);
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="container_content">
        <div class="container_content_inner">
            <div class="title">
                <h1 id="helloUser">Hello User</h1>
            </div>
            <div class="par">
                <p>Welcome to your Task Manager's Dashboard!</p>
                <p>Here you can manage your tasks efficiently with our platform.</p>
                <p>Easily manage your to-do list, set priorities, and track progress effortlessly. Our user-friendly platform empowers you to stay organized and focused on your goals. Welcome to a smarter way of managing tasks – where productivity meets simplicity. Your success begins with effective task management.</p>
            </div>
            <div class="btns">
                <button class='btns_more' onclick="redirectToDashboardPage()">My Dashboard</button>
            </div>
        </div>
    </div>
    <div class="container_outer_img">
        <div class="img-inner">
            <img src='https://www.shutterstock.com/image-vector/business-planning-task-management-concept-260nw-1987578881.jpg'  alt="" class="container_img"/>
        </div>
    </div>
</div>
<div class="overlay"></div>
</body>
<script src="<c:url value="/resource/support/js/appjs/introTaskManager.js"/>"></script>
</html>
