$(document).ready(function () {
    $("#addTaskBtn").click(function () {
        var userId = sessionStorage.getItem('userId');
        if (userId != null) {
            addTask();
        } else {
            window.location.href = "/TaskManager1/login";
        }
    });
});

function addTask() {
    var userId = sessionStorage.getItem('userId');
    var title = document.getElementById('title').value;
    if (title.split(/\s+/).length > 20) {
        alert('Title should have a maximum of 100 words.');
        return false;
    }

    var description = document.getElementById('description').value;
    var dueDate = document.getElementById('dueDate').value;
    var today = new Date().toISOString().split('T')[0];
    if (dueDate < today) {
        alert('Due date cannot be older than today.');
        return false;
    }

    var priority = document.getElementById('priority').value;
    if (priority < 0) {
        alert('Priority should not be negative.');
        return false;
    }

    if (!document.getElementById('title').checkValidity()) {
        alert("Invalid title. Please enter only letters and spaces.");
        return;
    }

    var taskData = {
        "userId": userId,
        "title": title,
        "description": description,
        "dueDate": dueDate,
        "priority": priority
    };

    let header = {
        userId: userId,
    }

    $.ajax({
        type: "POST",
        url: "/TaskManager1/addTask",
        contentType: "application/json",
        headers: header,
        data: JSON.stringify(taskData),
        success: function (task) {
            if (task) {
                sessionStorage.setItem("taskId", task.id);
                Swal.fire({
                    title: 'Task added Successfully !!',
                    icon: 'info',
                    showConfirmButton: false,
                    timer: 1500
                }).then(function () {
                    window.location.replace("/TaskManager1/introTaskManager/userDashboard");
                });
            } else {
                console.error("Error: Task or taskId not available in the response");
            }
        },
        error: function () {
            alert("Error adding Task ");
        }
    });
}
