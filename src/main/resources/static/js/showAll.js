// js/showAll.js
$(document).ready(function() {
    $('#showButton').on('click', function() {
        $.ajax({
            url: '/employees/showAll',
            method: 'GET',
            success: function(data) {
                const employeeTableBody = $('#employeeTableBody');
                employeeTableBody.empty(); // Clear existing rows

                // Add JSON data to the table
                data.forEach(function(employee) {
                    const row = `
                        <tr data-id="${employee.id}">
                            <td>${employee.id}</td>
                            <td>${employee.name}</td>
                            <td>${employee.surname}</td>
                            <td>${employee.email}</td>
                            <td>${employee.active}</td>
                            <td>
                                <div class="d-flex justify-content-center">
                                    <button type="button" class="btn btn-info btn-sm update-btn" data-id="${employee.id}">Update</button>
                                    <button type="button" class="btn btn-danger btn-sm delete-btn" data-id="${employee.id}">Delete</button>
                                </div>
                            </td>
                        </tr>
                    `;
                    employeeTableBody.append(row);
                });
            },
            error: function(err) {
                console.error('Error retrieving employee list:', err);
            }
        });
    });
});
