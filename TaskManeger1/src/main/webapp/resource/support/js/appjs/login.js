function loginUser() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var userData = {
        "email": email,
        "password": password
    };

    $.ajax({
        type: "POST",
        url: "/TaskManager1/login",
        contentType: "application/json",
        data: JSON.stringify(userData),
        success: function (userDto) {
            if (userDto && userDto.id) {
                sessionStorage.setItem("userId", userDto.id);
                Swal.fire({
                    title: 'Login successfully!',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                }).then(function () {
                    window.location.href = "/TaskManager1/login/introTaskManager";
                });
            } else {
                Swal.fire({
                    title: 'User not exist!',
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        },
        error: function (error) {
            Swal.fire({
                title: 'Login failed. Please check your username and password.',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}

