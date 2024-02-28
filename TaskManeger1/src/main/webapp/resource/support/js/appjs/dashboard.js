$(document).ready(function () {
    $("#getUserDetailsBtn").click(function () {
        var userId = sessionStorage.getItem("userId");

        if (userId !== null) {
            let header = {
                userId: userId,
            };
            $.ajax({
                type: "GET",
                url: "/TaskManager1/introTaskManager/dashboard",
                contentType: "application/json",
                headers: header,
                success: function (response) {
                    showUserDetails(response);
                    $("#myPopup").show();
                },
                error: function (error) {
                    alert("Error fetching data" + error);
                }
            });
        } else {
            window.location.href = "/TaskManager1/login";
        }
    });
    $("#closePopup").click(function () {
        $("#myPopup").hide();
    });
});

function showUserDetails(userData) {
    var tableBody = $("#userTable tbody");
    tableBody.empty();

    var row = $("<tr>");
    row.append($("<td>").text(userData.firstName));
    row.append($("<td>").text(userData.lastName));
    row.append($("<td>").text(userData.email));

    tableBody.append(row);
}

function redirectToTaskPage() {
    window.location.href = "/TaskManager1/addTask";
}

function displayTaskPage() {
    window.location.href = "/TaskManager1/displayTasks";
}

function redirectToSortPage() {
    window.location.href = "/TaskManager1/sortByPriority";
}

function redirectToEditTaskPage() {
    window.location.href = "/TaskManager1/edit";
}

function redirectToDisplayPage() {
    window.location.href = "/TaskManager1/displayCompletedTasks";
}

// function logout() {
//     window.location.href = "/TaskManager1/login";
// }

function redirectToRemovePage() {
    var userId = sessionStorage.getItem("userId");

    if (!userId) {
        return;
    }

    let header = {
        userId: userId,
    }

    $.ajax({
        type: "GET",
        url: "/TaskManager1/removeCompletedTasks/",
        headers: header,
        success: function (response) {
            if (response.includes("Task removed successfully !")) {
                Swal.fire({
                    title: 'Task Removed successfully!',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                Swal.fire({
                    title: 'No task completed Yet!',
                    icon: 'info',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        },
        error: function (error) {
            Swal.fire({
                title: 'No task completed Yet!',
                icon: 'info',
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
}


$("#logoutBtn").click(function () {
    $.ajax({
        type: "POST",
        url: "/TaskManager1/logout",
        contentType: "application/json",
        success: function (response) {
            // Invalidate session on the server-side
            sessionStorage.clear();
            localStorage.clear();
            //  Prevent back and forward buttons from working
            history.pushState(null, null, window.location.href);
            window.onpopstate = function () {
                history.go(-3);
            };
            // Redirect to login page
            window.location.href = "/TaskManager1/login";
        },
        error: function (error) {
        }
    });
});

