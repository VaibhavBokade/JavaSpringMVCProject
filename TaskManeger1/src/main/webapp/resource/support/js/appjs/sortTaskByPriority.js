$(document).ready(function () {
    var userId = sessionStorage.getItem('userId');
    if (userId) {
        redirectToSortPage(userId);
    }
});

function redirectToSortPage(userId) {
    let header = {
        userId : userId,
    }
    $.ajax({
        type: "GET",
        url: "/TaskManager1/sortTasksByPriority",
        dataType: 'json',
        headers : header,
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
        error: function () {
            alert("Error fetching tasks");
        }
    });
}