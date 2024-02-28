function registerUser() {
    var firstName = document.getElementById('firstName').value;
    var lastName = document.getElementById('lastName').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    if (!validateName(firstName, "First Name")) {
        return false;
    }

    if (!validateName(lastName, "Last Name")) {
        return false;
    }
    if (!validateEmail(email)) {
        return false;
    }

    if (!validatePassword(password)) {
        return false;
    }

    var userData = {
        "firstName": firstName,
        "lastName": lastName,
        "email": email,
        "password": password
    };

    $.ajax({
        type: "POST",
        url: "/TaskManager1/register",
        contentType: "application/json",
        data: JSON.stringify(userData),
        success: function () {
            Swal.fire({
                title: 'You have Registered Successfully !!',
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            }).then(function () {
                window.location.href = '/TaskManager1/login';
            });
        },
        error: function (error) {
            if (error.status === 409) {
                Swal.fire({
                    title: 'User with this email already exists.',
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                Swal.fire({
                    title: 'Error adding User: ' + error.statusText,
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        }
    });
    return false;
}

function validateName(name, fieldName) {
    var nameRegex = /^[a-zA-Z]+$/;
    if (!name.match(nameRegex)) {
        alert("Please enter only letters for " + fieldName);
        return false;
    }
    return true;
}

// Function to validate password complexity
function validatePassword(password) {
    var passwordRegex = /^[a-zA-Z@$!%*?&]+\d+$/;
    if (!password.match(passwordRegex)) {
        alert('Password must contain at least one letter, one number, and one special character.');
        return false;
    }
    return true;
}
function validateEmail(email) {
    var emailRegex = /^[a-zA-Z]+\d+@gmail\.com$/;
    if (!email.match(emailRegex)) {
        alert('Email must contain at least one letter, one number, and one special character.');
        return false;
    }
    return true;
}

$(document).ready(function () {
    $("#registrationForm").submit(function () {
        return registerUser();
    });
});

