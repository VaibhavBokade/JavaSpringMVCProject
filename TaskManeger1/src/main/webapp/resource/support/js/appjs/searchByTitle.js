$(document).ready(function() {
    $("#searchButton").click(function() {
        var userId = sessionStorage.getItem('userId');
        var title = $("#titleInput").val();
        if (userId) {
            searchTasksByTitle(userId, title);
        }
    });
});

function searchTasksByTitle(userId,title) {
    let header = {
        userId : userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/searchByTitle",
        headers : header,
        data: { title: title },
        success: function(tasks) {
            console.log("Received tasks:", tasks);
            displayTasks(tasks);
        },
        error: function(error) {
            alert("Error searching tasks by title: " + error);
        }
    });
}

function displayTasks(tasks) {
    $("#tasksTableBody").empty();
    if (tasks.length > 0) {
        var task = tasks[0];
        var row = "<tr>" +
            "<td>" + task.id + "</td>" +
            "<td>" + task.title + "</td>" +
            "<td>" + task.description + "</td>" +
            "<td>" + task.dueDate + "</td>" +
            "<td>" + task.priority + "</td>" +
            "<td>" + task.status + "</td>" +
            "</tr>";
        $("#tasksTableBody").append(row);
    } else {
        console.log("No tasks found in the response");
    }
}
