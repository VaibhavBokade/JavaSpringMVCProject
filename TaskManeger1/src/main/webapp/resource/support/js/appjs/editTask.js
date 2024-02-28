
$(document).ready(function () {
    var userId = sessionStorage.getItem('userId');
    if (userId != null) {
        var taskDetails = JSON.parse(sessionStorage.getItem('editTask'));

        // Populate the form fields with task details
        $("#title").val(taskDetails.title);
        $("#description").val(taskDetails.description);
        $("#priority").val(taskDetails.priority);
        $("#dueDate").val(taskDetails.dueDate);
        $("#status").val(taskDetails.status);

        // ... (rest of your existing code)

        // Modify the "Submit" button click event to call the editTask function
        $("#editTaskForm").submit(function (event) {
            event.preventDefault();
            editTask();
        });
    }else {
        window.location.href = "/TaskManager1/login";
    }
});

function editTask() {
    var userId = sessionStorage.getItem('userId');
    var title = $("#title").val();
    var description = $("#description").val();
    var priority = $("#priority").val();
    var dueDate = $("#dueDate").val();
    var status = $("#status").val();

    var updatedTask = {
        "title": title,
        "description": description,
        "priority": priority,
        "dueDate": dueDate,
        "status": status
    };

    var headers = {
        "userId": userId,
        "title": title
    };

    $.ajax({
        type: "PUT",
        url: "/TaskManager1/editTask",
        contentType: "application/json",
        data: JSON.stringify(updatedTask),
        headers: headers,
        success: function (response) {
            alert(response);
            window.location.replace("/TaskManager1/introTaskManager/userDashboard");
        },
        error: function (error) {
            alert("Error editing task: " + error.statusText);
        }
    });
}
