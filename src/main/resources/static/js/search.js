$(document).ready(function () {
    const $keywordInput = $("#keyword")
    // search function
    function searchEmployees() {
        let keyword = $keywordInput.val().trim(); // Get and trim input value
        // if the keyword is empty, return
        if (!keyword) {
            return;
        }
        // AJAX call to search employees
        $.ajax({
            url: '/employees/search', // search endpoint
            type: 'GET',
            data: { keyword: keyword }, // send the keyword as a parameter
            success: function (response) {
                // clear the table body
                $("#employeeTableBody").empty();
                // fill the table body with employee data
                response.forEach(function (employee) {
                    $("#employeeTableBody").append(`
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
                    `);
                });
            },
            error: function () {
                alert('Error occurred while searching!');
            }
        });
    }
    // keyup event listener for the input field
   $keywordInput.on('keyup',function () {
       searchEmployees();
   })

    // click event listener for the search button
    $("#searchButton").on('click', function (e) {
        e.preventDefault();
        searchEmployees(); // search function on button click
    });
});
