<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Login</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px; /* Set a fixed width for better presentation */
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: calc(100% - 16px);
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            display: inline-block;
            vertical-align: middle;
        }

        #togglePassword {
            display: inline-block;
            vertical-align: middle;
            cursor: pointer;
            margin-left: -30px;
            color: #888;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        h2 {
            margin-bottom: 20px;
            text-align: center;
        }
    </style>
</head>

<body>

<form action="loginUser" method="post" id="loginForm">
    <h2>Login</h2>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" placeholder="Your email address" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Your password" required>
    <i class="far fa-eye" id="togglePassword"></i>
    <button type="button" onclick="loginUser()">Submit</button>
</form>

<script src="<c:url value="/resource/support/js/appjs/login.js"/>"></script>
<script>
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    togglePassword.addEventListener('click', function (e) {
        // Toggle the type attribute
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // Toggle the eye icon
        this.classList.toggle('fa-eye-slash');
    });
</script>
</body>
</html>
