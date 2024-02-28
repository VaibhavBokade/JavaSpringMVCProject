function redirectToIntroPage() {
    $.ajax({
        type: "GET",
        url: "/TaskManager1/login/introTaskManager/",
        dataType: 'json',
        success: function () {
            // Redirect to the introTaskManager page
            window.location.href = "/TaskManager1/login/introTaskManager";
        },
        error: function () {
            alert("Error fetching tasks");
        }
    });
}

function redirectToDashboardPage() {
    window.location.href = "/TaskManager1/introTaskManager/userDashboard";
}