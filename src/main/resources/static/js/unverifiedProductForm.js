
    $("form").submit(function () {
        // var formData = JSON.stringify($(this).serializeArray());
        console.log($(this).formToJson());
        const formData = $(this).formToJson();
        return false;
    });


    $(function () {
        var $select = $(".1-27");
        for (i = 1; i <= 27; i++) {
            $select.append($('<option></option>').val(i).html(i))
        }
    });
