$(document).ready(function() {
    var table = $('#userTable').DataTable({
        responsive: true,
        columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0
        }],
        select: {
            style: 'multi',
            selector: 'td:first-child'
        }
    });
    getProjects();

    function getProjects() {
        $.ajax({
            url : '/projects',
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    console.log(response[i]);
                    $('#projectName').append('<option>' + response[i] + '</option>');
                }
            }
            })
    }

    $('#projectName').on('change', function () {
        $.ajax({
            url: "/project-users",
            data: 'projectName=' + $('#projectName').val(),
            success : function (response) {
                table.clear()
                for (var i = 0; i < response.length; i++) {
                    table.row.add([
                        i + 1,
                        response[i]
                    ]).draw( false );
                }
            }
        });
    })
});