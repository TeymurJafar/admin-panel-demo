// delete.js
$(document).ready(function() {
    // Delete button click
    $(document).on('click', '.delete-btn', function() {
        const employeeId = $(this).data('id'); // get employee id from data-id attribute

        // confirm before delete
        if (confirm("Are you sure you want to delete this employee?")) {
            $.ajax({
                url: '/employees/delete', // delete endpoint
                type: 'POST', // use POST method
                contentType: 'application/json', // send data in JSON format
                data: JSON.stringify(employeeId), // Send ID directly in JSON format
                cache: false,
                success: function() {
                    console.log("Employee Id: " + employeeId);
                    // Update employee list
                    $('#employeeTableBody').find(`tr[data-id='${employeeId}']`).remove();  // remove table row
                    // Success status for deletion
                    alert('Employee successfully deleted.');

                },
                error: function(xhr, status, error) {
                    // error handling
                    alert('An error occurred while deleting the employee: ' + xhr.responseText);
                }
            });
        }
    });
});
