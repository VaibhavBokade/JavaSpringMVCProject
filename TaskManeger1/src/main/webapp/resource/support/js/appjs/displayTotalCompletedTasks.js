
                                                                                                                           $(document).ready(function () {
    $("#displayCompletedTaskBtn").click(function () {
        var userId = sessionStorage.getItem('userId');
        if (userId != null) {
            displayTaskPage(userId);
        } else {
            window.location.href = "/TaskManager1/login";
        }
    });
});

function displayTaskPage(userId) {
    let header = {
        userId: userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/displayTotalCompletedTasks",
        dataType: 'json',
        headers: header,
        success: function (tasks) {
            $.each(tasks, function (index, task) {
                var row = "<tr>" +
                    "<td>" + (index + 1) + "</td>" +
                    "<td>" + task.title + "</td>" +
                    "<td>" + task.description + "</td>" +
                    "<td>" + task.dueDate + "</td>" +
                    "<td>" + task.priority + "</td>" +
                    "</tr>";
                $("#tasks").append(row);
            });
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
