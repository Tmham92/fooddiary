//Author Hans Zijlstra

$(document).ready(function() {
    //Initiate datatable of users
    var table = $('#userTable').DataTable({
        responsive: true,
        columnDefs: [{
            orderable: false,
            className: 'select-checkbox',
            targets: 0,
            'checkboxes': {
                'selectRow': true
            }
        }],
        select: {
            style: 'multi',
            selector: 'td:first-child'
        }
    });
    table.columns( [2] ).visible( false );


    var currentTab = 0; // Current tab is set to be the first tab (0)
    showTab(currentTab); // Display the current tab
    getProjects();

    $("#nextBtn").click(function () {
        nextPrev(1)

    });

    $("#prevBtn").click(function () {
        nextPrev(-1)

    });

    //multiform field function
    function showTab(n) {
        // This function will display the specified tab of the form ...
        var x = document.getElementsByClassName("tab");
        x[n].style.display = "block";
        // ... and fix the Previous/Next buttons:
        if (n == 0) {
            document.getElementById("prevBtn").style.display = "none";

        } else {
            document.getElementById("prevBtn").style.display = "inline";
        }
        if (n == (x.length - 1)) {
            document.getElementById("nextBtn").innerHTML = "Submit";
        } else {
            document.getElementById("nextBtn").innerHTML = "Next";
        }
        // ... and run a function that displays the correct step indicator:
        fixStepIndicator(n)
    }

    // A form on submit that creates food diary reports in csv to be downloaded
    $("#regForm").on("submit", function (e) {
        e.preventDefault();
        var rowCount = table.rows({selected: true}).count();
        data = {};
        data['dateFrom'] = $('#date-from').val();
        data['dateTil'] = $('#date-til').val();
        data['users'] = [];
        var dataRows = table.rows('.selected').data();
        for (var i = 0; i < rowCount; i++) {
            data["users"].push(dataRows[i][2])
        }

        $.ajax({
            url : '/fetch-report',
            type: "POST",
            dataType: 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data),
            success: function (response) {
                $(".control-buttons-tab").hide()
                $(".circle-steps").hide()

                for (var i = 0; i < response.length; i++) {
                    var len = response[i].toString().split("\\").length - 1;
                    var arr = response[i].toString().split("\\");
                    var linklink = $('<p>').append($('<a>',{
                        text: arr[len],
                        title: 'some title',
                        href: response[i]
                    })).appendTo('#regForm');
                }
            }
        })
    });

    // Option button to go to the next tab
    function nextPrev(n) {
        // This function will figure out which tab to display
        var x = document.getElementsByClassName("tab");
        // Exit the function if any field in the current tab is invalid:
        // Hide the current tab:
        x[currentTab].style.display = "none";
        // Increase or decrease the current tab by 1:
        currentTab = currentTab + n;
        // if you have reached the end of the form... :
        if (currentTab >= x.length) {
            //...the form gets submitted:
            document.getElementById("nextBtn").type = "submit";
            return false;
        }
        // Otherwise, display the correct tab:
        showTab(currentTab);
    }

    function validateForm() {
        // This function deals with validation of the form fields
        var x, y, i, valid = true;
        x = document.getElementsByClassName("tab");
        y = x[currentTab].getElementsByTagName("input");
        // A loop that checks every input field in the current tab:
        for (i = 0; i < y.length; i++) {
            // If a field is empty...
            if (y[i].value == "") {
                // add an "invalid" class to the field:
                y[i].className += " invalid";
                // and set the current valid status to false:
                valid = false;
            }
        }
        // If the valid status is true, mark the step as finished and valid:
        if (valid) {
            document.getElementsByClassName("step")[currentTab].className += " finish";
        }
        return valid; // return the valid status
    }


    // Indicator to tell on which tab the user is of the form field process
    function fixStepIndicator(n) {
        // This function removes the "active" class of all steps...
        var i, x = document.getElementsByClassName("step");
        for (i = 0; i < x.length; i++) {
            x[i].className = x[i].className.replace(" active", "");
        }
        //... and adds the "active" class to the current step:
        x[n].className += " active";
    }



    table.on("click", "th.select-checkbox", function() {
        if ($("th.select-checkbox").hasClass("selected")) {
            table.rows().deselect();
            $("th.select-checkbox").removeClass("selected");
        } else {
            table.rows().select();
            $("th.select-checkbox").addClass("selected");
        }
    }).on("select deselect", function() {
        // ("Some selection or deselection going on")
        if (table.rows({
            selected: true
        }).count() !== table.rows().count()) {
            $("th.select-checkbox").removeClass("selected");
        } else {
            $("th.select-checkbox").addClass("selected");
        }
    });


    // Returns all the projects within the database
    function getProjects() {
        $.ajax({
            url : '/projects',
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    $('#projectName').append('<option>' + response[i] + '</option>');
                }
            }
            })
    }
    // Returns all the users within a project
    $('#projectName').on('change', function () {
        $.ajax({
            url: "/project-users",
            data: 'projectName=' + $('#projectName').val(),
            success : function (response) {
                table.clear();
                for (var i = 0; i < response.length; i++) {
                    var obj = response[i];
                    table.row.add([
                        i + 1,
                        obj.user_code,
                        obj.id
                    ]).draw(false)
                }
            }
        });
    })
});