// addOrUpdate.js
$(document).ready(function() {
    $(document).on('click', '.update-btn', function() {
        // Get the ID from the button
        const row = $(this).closest('tr'); // get the row which update button exists
        const id = $(this).data('id');
        console.log("ID to be updated: " + id);

        // Fill the form
        $('#id').val(id); // Hidden ID field
        $('#name').val(row.find('td:eq(1)').text()); // Set name from table row
        $('#surname').val(row.find('td:eq(2)').text()); // Set surname from table row
        $('#email').val(row.find('td:eq(3)').text()); // Set email from table row
    });

    // Handle form submission for updating or adding
    $('#addOrUpdateForm').on('submit', function(event) {
        event.preventDefault(); // Prevent the default form submission behavior

        // Get the values from the form
        const id = $('#id').val();
        const name = $('#name').val();
        const surname = $('#surname').val();
        const email = $('#email').val();

        // AJAX call for adding or updating
        $.ajax({
            url: '/employees/addEmployee', // Using a single endpoint
            type: 'POST', // POST method for both adding and updating
            contentType: 'application/json',
            data: JSON.stringify({
                id: id, // ID can be null or empty, the backend will handle this
                name: name,
                surname: surname,
                email: email
            }),
            success: function(employee) {
                // If an ID exists, update the row in the table
                if (id) {
                    const row = $('button[data-id="' + id + '"]').closest('tr');
                    row.find('td:eq(1)').text(employee.name);
                    row.find('td:eq(2)').text(employee.surname);
                    row.find('td:eq(3)').text(employee.email);
                    row.find('td:eq(4)').text(employee.active);
                } else {
                    // If it's a new record, add a new row to the table
                    const newRow = `<tr data-id="${employee.id}">
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td>${employee.email}</td>
                        <td>${employee.active}</td>
                        <td class="text-center" colspan="2" style="text-align: center; vertical-align: center">
                            <div class="d-flex justify-content-center">
                                <button type="button" class="btn btn-info btn-sm update-btn" data-id="${employee.id}">Update</button>
                                <button type="button" class="btn btn-danger btn-sm delete-btn" data-id="${employee.id}">Delete</button>
                            </div>
                        </td>
                    </tr>`;
                    $("#employeeTableBody").append(newRow); // Add the new row to the table
                }

                // Reset the form
                $('#addOrUpdateForm')[0].reset();
                $('#id').val(''); // clear the hidden ID field

                alert('The Employee successfully saved');
            },
            error: function(xhr) {
                alert('Error occurred: ' + xhr.responseText);
            }
        });
    });

});
