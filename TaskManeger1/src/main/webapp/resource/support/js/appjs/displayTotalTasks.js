$(document).ready(function () {
    var userId = sessionStorage.getItem('userId');

    if (userId != null) {
        displayTaskPage(userId);
    } else {
        window.location.href = "/TaskManager1/login";
    }

    $("#searchTask").click(function () {
        var searchInput = $("#searchInput").val();
        var searchBy = $("#searchBy").val();

        if (searchBy === "title") {
            searchTasksByTitle(userId, searchInput);
        } else if (searchBy === "dueDate") {
            searchTasksByDueDate(userId, searchInput);
        } else if (searchBy === "status") {
            getTaskStatus(userId, searchInput);
        }
    });
});

function displayTaskPage(userId) {
    let header = {
        userId: userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/displayTotalTasks",
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
                    "<td>";

                if (task.status === 'COMPLETED') {
                    row += "<input type='checkbox' class='status-checkbox' data-task-title='" + task.id + "' checked>";
                } else {
                    row += "<input type='checkbox' class='status-checkbox' data-task-title='" + task.id + "'>";
                }

                row += "</td>" +
                    "<td><button class='edit-task-btn' data-task-title='" + task.id + "'>Edit Task</button></td>" +
                    "<td><button class='delete-task-btn' data-task-title='" + task.id + "'>Delete Task</button></td>" +
                    "</tr>";
                $("#tasks").append(row);
            });

            $(".status-checkbox").change(function () {
                var userId = sessionStorage.getItem('userId');
                var taskId = $(this).data("task-title");
                var isChecked = $(this).prop("checked");
                markTaskAsCompleted(userId, taskId, isChecked, this);
            });

            $(".edit-task-btn").click(function () {
                var taskId = $(this).data("task-title");
                fetchTaskDetails(userId, taskId);
            });

            $(".delete-task-btn").click(function () {
                var taskId = $(this).data("task-title");
                var row = $(this).closest("tr");
                deleteTaskDetails(taskId, row);
            });

        },
        error: function () {
            alert("Error fetching tasks");
        }
    });
}

function fetchTaskDetails(userId, taskId) {
    var headers = {
        "userId": userId,
        "taskId": taskId
    };
    $.ajax({
        type: "GET",
        url: "/TaskManager1/getTaskDetails",
        headers: headers,
        success: function (task) {
            sessionStorage.setItem('editTask', JSON.stringify(task));
            redirectToEditTaskPage();
        },
        error: function (error) {
            alert("Error fetching task details: " + error.statusText);
        }
    });
}
function deleteTaskDetails(taskId, row) {
    var headers = {
        "taskId": taskId
    };
    $.ajax({
        type: "GET",
        url: "/TaskManager1/deleteTask",
        headers: headers,
        success: function (response) {
            if (response.includes("Task deleted successfully !")) {
                Swal.fire({
                    title: 'Task deleted successfully!',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                });
                row.remove();
            } else {
                Swal.fire({
                    title: 'No task found!',
                    icon: 'info',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        },
        error: function (error) {
            Swal.fire({
                title: 'Error in deleting task',
                icon: 'info',
                showConfirmButton: false,
                timer: 1500
            });
        }
    })
}



function markTaskAsCompleted(userId, taskId, isChecked, checkbox) {
    //var encodedTaskTitle = encodeURIComponent(taskTitle);
    var headers = {
        "userId": userId,
        "taskId": taskId,
    };

    $.ajax({
        type: "POST",
        url: "/TaskManager1/markTaskAsCompleted",
        headers: headers,
        contentType: "application/json",
        success: function (response) {
            console.log("Success Response:", response);
            if (response.includes("Task marked as completed successfully")) {
                Swal.fire({
                    title: 'Task marked as completed successfully!',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                });
                $(checkbox).prop("checked", isChecked);
                $(checkbox).prop("disabled", isChecked);
            } else {
                Swal.fire({
                    title: response,
                    icon: 'error'
                });
            }
        },
        error: function (error) {
            Swal.fire({
                title: 'Bad Request: Please check the parameters.',
                icon: 'error'
            });
        }
    });
}

function searchTasksByTitle(userId, title) {
    if(title === '') {
        Swal.fire({
            title: 'Please enter input....',
            icon: 'info',
            showConfirmButton: false,
            timer: 2000
        });
        return;
    }
    let header = {
        userId: userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/searchByTitle",
        headers: header,
        data: { title: title },
        success: function (tasks) {
            console.log("Received tasks:", tasks);
            displaySearchedTasks(tasks);
        },
        error: function (error) {
            Swal.fire({
                title: 'This task is not present in your list....',
                icon: 'info',
                showConfirmButton: false,
                timer: 2000
            });
        }
    });
}

function displaySearchedTasks(tasks) {
    $("#tasks").empty();
    if (tasks.length > 0) {
        $.each(tasks, function (index, task) {
            var row = "<tr>" +
                "<td>" + task.id + "</td>" +
                "<td>" + task.title + "</td>" +
                "<td>" + task.description + "</td>" +
                "<td>" + task.dueDate + "</td>" +
                "<td>" + task.priority + "</td>" +
                "<td>" + task.status + "</td>";

                row += "<td><button class='edit-task-btn' data-task-title='" + task.title + "'>Edit Task</button></td>";

            row += "</tr>";
            $("#tasks").append(row);
        });
        $(".edit-task-btn").click(function () {
            var taskTitle = $(this).data("task-title");
            sessionStorage.setItem('editTaskTitle', taskTitle);
            redirectToEditTaskPage();
        });
    } else {
        console.log("No tasks found in the response");
    }
}

function searchTasksByDueDate(userId, dueDate) {
    if(dueDate === '') {
        Swal.fire({
            title: 'Please enter input....',
            icon: 'info',
            showConfirmButton: false,
            timer: 2000
        });
        return;
    }
    let header = {
        userId: userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/searchByDueDate",
        headers: header,
        data: { dueDate: dueDate },
        success: function (tasks) {
            displaySearchedTasks(tasks);
        },
        error: function (error) {
            Swal.fire({
                title: 'This task with '+ dueDate + ' this due date is not present in your list',
                icon: 'info',
                showConfirmButton: true,
                timer: 4000
            });
        }
    });
}

function getTaskStatus(userId, title) {
    let header = {
        userId: userId,
        title: title
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/showStatus",
        headers: header,
        data: { title: title },
        success: function (response) {
            if (response === "COMPLETED")  {
                Swal.fire({
                    title: 'This task is completed Successfully....!',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                });
            } else if (response === "NOT_COMPLETED"){
                Swal.fire({
                    title: 'This task is not completed yet....!',
                    icon: 'error'
                });
            }
        },error: function (error) {
            Swal.fire({
                title: 'This task is not present in your list"',
                icon: 'info',
                showConfirmButton: false,
                timer: 2000
            });
        }
    })
}

function redirectToSortByPriorityPage() {
    var userId = sessionStorage.getItem('userId');
    let header = {
        userId : userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/sortTasksByPriority",
        dataType: 'json',
        headers : header,
        success: function (tasks) {
            displaySearchedTasks(tasks);
        },
        error: function () {
            alert("Error fetching tasks");
        }
    });
}

function redirectToSortPage() {
    var userId = sessionStorage.getItem('userId');
    let header = {
        userId : userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/sortTasksByDueDate",
        dataType: 'json',
        headers : header,
        success: function (tasks) {
            displaySearchedTasks(tasks);
        },
        error: function () {
            alert("Error fetching tasks");
        }
    });
}
function redirectToEditTaskPage() {
    window.location.href = "/TaskManager1/edit";
}

// function editTask() {
//     var userId = sessionStorage.getItem('userId');
//     var title = document.getElementById('title').value;
//     var description = document.getElementById('description').value;
//     var priority = document.getElementById('priority').value;
//     var dueDate = document.getElementById('dueDate').value;
//     var status = document.getElementById('status').value;
//
//     var updatedTask = {
//         "title": title,
//         "description": description,
//         "priority": priority,
//         "dueDate": dueDate,
//         "status": status
//     };
//
//     var headers = {
//         "userId": userId,
//         "title": title
//     };
//
//     $.ajax({
//         type: "PUT",
//         url: "/TaskManager1/editTask",
//         contentType: "application/json",
//         data: JSON.stringify(updatedTask),
//         headers: headers,
//         success: function (response) {
//             alert(response);
//             window.location.href = '/TaskManager1/introTaskManager/dashboard';
//         },
//         error: function (error) {
//             alert("Error editing task: " + error.statusText);
//         }
//     });
// }

function editTask() {
    var userId = sessionStorage.getItem('userId');
    var title = document.getElementById('title').value;

    // Fetch existing task details
    $.ajax({
        type: "GET",
        url: "/TaskManager1/getTaskDetails",
        data: { userId: userId, title: title },
        success: function (task) {
            // Update only the fields provided by the user
            var description = document.getElementById('description').value || task.description;
            var priority = document.getElementById('priority').value || task.priority.toString();
            var dueDate = document.getElementById('dueDate').value || task.dueDate;
            var status = document.getElementById('status').value || task.status;

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

            // Send the updated task details to the backend
            $.ajax({
                type: "PUT",
                url: "/TaskManager1/editTask",
                contentType: "application/json",
                data: JSON.stringify(updatedTask),
                headers: headers,
                success: function (response) {
                    alert(response);
                    window.location.href = '/TaskManager1/introTaskManager/dashboard';
                },
                error: function (error) {
                    alert("Error editing task: " + error.statusText);
                }
            });
        },
        error: function (error) {
            alert("Error fetching task details: " + error.statusText);
        }
    });
}

