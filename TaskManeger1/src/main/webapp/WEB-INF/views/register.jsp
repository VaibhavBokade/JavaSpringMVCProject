<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>Registration form</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: cornflowerblue;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: url("https://i.pinimg.com/originals/bf/fe/8d/bffe8d5e9c5ea4aa0fbbfc3b417d2018.jpg");
            background-size: cover;

        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<form action="/registerUser" method="post" >
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" placeholder="Your first name" required>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" placeholder="Your last name" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" placeholder="Your email address" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}">
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Your password" required>


    <button type="button" onclick="registerUser()">Register</button>
</form>
<script src="<c:url value="/resource/support/js/appjs/registration.js"/>"></script>
</body>

</html>

