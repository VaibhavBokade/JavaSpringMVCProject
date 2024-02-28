// on click listener
// get al tehe fields valure
// call backebd api

$(document).ready(function () {
    // Add click event listener to the Save Product button
    $("#saveProductBtn").click(function (event) {
        // Prevent the default form submission
        event.preventDefault();

        // Call the function to handle the AJAX request
        ajaxCall();
    });

    // Function to handle the AJAX request
    function ajaxCall() {
        $.ajax({
            // Our sample url to make the request
            url: 'http://localhost:8080/ProductCrud/',

            // Type of Request
            type: "POST",

            // Data to be sent (if needed)
            data: $("#productForm").serialize(),

            // Function to call when the request is successful
            success: function (data) {
                let x = JSON.stringify(data);
                console.log(x);
            },

            // Error handling
            error: function (error) {
                console.log(`Error ${error}`);
            }
        });
    }
});
