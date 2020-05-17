//author: Hans Zijlstra

$(document).ready(function() {
    var diaryTable;
    var descriptions = getDescriptions();
    getHistoryItems();
    autocomplete(document.getElementById("productDescription"), descriptions);


    autocomplete(document.getElementById("productInput"), descriptions);

    // make rows able for selecting
    $('#diaryTable tbody').on('click', 'tr', function (e) {
       if ( $(this).hasClass('row_selected')) {
           $(this).removeClass('row_selected');


       } else {
           diaryTable.$('tr.selected_row');
           $(this).addClass('row_selected');
       }

    });

    $('#btn-remove').click(function(){
        var anSelected = fnGetSelected( diaryTable );
        for (var i = 0; i < anSelected.length; i++) {
            var data = $('#diaryTable').DataTable().row(anSelected[i]).data();
            $.ajax({
                url : "/remove/diary-entry",
                type: "POST",
                dataType: 'json',
                data: 'entry=' + data[0],
                success: function (response) {
                    console.log(response)
                }


            })
        }
        $(anSelected).remove();
    });




    /* Get the rows which are currently selected */
    function fnGetSelected( diaryTableLocal )
    {
        return diaryTableLocal.$('tr.row_selected');
    }

    diaryTable = $('#diaryTable').DataTable();
    diaryTable.columns( [0] ).visible( false );

    getTodaysDiaryEntries(diaryTable);

    $("#diaryDate").change(function (event) {
        event.preventDefault();
        diaryTable.clear();
        $.ajax({
            url: '/diary-by-date',
            dataType: 'json',
            data: 'date=' +  $("#diaryDate").val(),
            success: function (response) {
                for (var i = 0; i < response.length; i++) {
                    var obj= response[i];
                    diaryTable.row.add([
                        obj.id,
                        obj.mealtime,
                        obj.productDescription,
                        obj.quantity + obj.unit,
                        obj.time,
                        obj.description
                    ]).draw(false);

                }
            }
        })


    });



    $("#product-entry").submit(function(event){
        event.preventDefault();


        var data = {};

        data["productDescription"] = $("#productDescription").val();
        data["mealtime"] = $("#mealtime").val();
        data["quantity"] = $("#quantity").val();
        data["unit"] = $("#unit").val();
        data["date"] = $("#date").val();
        data["time"] = $("#time").val();
        data["description"] = $("#description").val();

        console.log(data.productDescription)

        if (!descriptions.includes(data.productDescription)) {
            document.getElementById("productDescription").setCustomValidity("The product you entered does not appear in our database");
        } else {
            $.ajax({
                url : "/diary-entry/addtodiary",
                type: "POST",
                dataType: 'json',
                data: data,
                success: function (response) {
                    diaryTable.row.add([
                        response.id,
                        response.mealtime,
                        response.productDescription,
                        response.quantity + response.unit,
                        response.time,
                        response.description
                    ]).draw( false );

                }
            });
        }

    });

    // @Author Tom Wagenaar
    // Variable used to make the input id's distinct from each other.
    var counter = 1;

    // Function that makes a new div consisting of the product the uses wants to submit and a remove product button.
    $('#recipeAddProduct').click(function() {
        //
        var inputProduct = document.getElementById("productInput").value;
        var inputProductQuantity = document.getElementById("productQuantity").value;
        var inputProductQuantityUnit = document.getElementById("productQuantityUnit").value;

        inputProduct = inputProduct.trim();
        inputProductQuantity = inputProductQuantity.trim();
        inputProductQuantityUnit = inputProductQuantityUnit.trim();

        if (!checkRecipeInput(inputProduct, inputProductQuantity, inputProductQuantityUnit, descriptions)) {
            return false;
        }


        var recipeProduct = inputProductQuantity + " " + inputProductQuantityUnit + " " + inputProduct;

        // Make a new div consisting out of a text field with the product the user wants to add to the recipe + a button
        // to remove the product.
        var addedProductDiv = document.createElement('div');
        addedProductDiv.id = "productInput" + counter;

        addedProductDiv.innerHTML = "<input id='recipeProuct"+counter+"' type='text' placeholder='"+recipeProduct+"' style='width: 83%;' readonly>" +
            "<input type='button' value='-' onclick='removeProductLine(this)' style='width: 15%;margin-left: 5px'>";

        document.getElementById('dyanamicProductInput[0]').appendChild(addedProductDiv);


        var hiddenProductData = document.createElement('div');
        hiddenProductData.id = "hiddenProductInput" + counter;

        hiddenProductData.innerHTML =
            "<input id = 'productInput"+counter+"' type='text' name='recipeInput[]' value='"+inputProduct+"' hidden>" +
            "<input id='productInputQuantity"+counter+"' type='text' name='recipeInputQuantity[]' value='"+inputProductQuantity+"' hidden>" +
            "<input id='inputProductQuantityUnit"+counter+"' type='text' name='recipeInputQuantityUnit[]' value='"+inputProductQuantityUnit+"' hidden>";


        document.getElementById('dyanamicProductInput[0]').appendChild(hiddenProductData);

        // Clear the product in the input field
        document.getElementById("productInput").value = "";
        document.getElementById("productQuantity").value = "";
        document.getElementById("productQuantityUnit").value = "";

        counter++;
        console.log("Added a new product input field in the recipe form.")
    });

});

// @Author Tom Wagenaar
// Function that removes the current product and decreases the counter.
function removeProductLine(btn) {
    btn.parentNode.remove();
    counter--;
    console.log("Removed a product input field in the recipe form.")
}

function checkRecipeInput(inputProduct, inputProductQuantity, inputProductQuantityUnit, descriptions) {

    if (!descriptions.includes(inputProduct)) {
        console.log("This product: " + inputProduct + " isn't in the database!");
        // document.getElementById("productInput").setCustomValidity("The product you entered does not appear in our database");
        alert("This product: " + inputProduct + " isn't in the database!");
        return false;
    }

    if (!/^\d+$/.test(inputProductQuantity)) {
        alert("Please make sure that the quantity is a positive number!");
        return false;
    }

    if (!/\b(?:g|ml|)\b/.test(inputProductQuantityUnit)) {
        alert("Unit must be either g or ml!")
        return false;
    }



    return true;
}


$("#recipeSubmit").click(function (event) {

    event.preventDefault();

    var inputList = [];
    var inputQuantity = [];
    var inputQuantityUnit = [];

    var inputFields = document.getElementsByName("recipeInput[]");
    var inputFieldsQuantity = document.getElementsByName("recipeInputQuantity[]");
    var inputFieldsQuantityUnit = document.getElementsByName("recipeInputQuantityUnit[]");


    for (var i = 0; i < inputFields.length; i++)
        inputList[i] = inputFields[i].value;

    for (var i = 0; i < inputFieldsQuantity.length; i++)
        inputQuantity[i] = inputFieldsQuantity[i].value;

    for (var i = 0; i < inputFieldsQuantityUnit.length; i++)
        inputQuantityUnit[i] = inputFieldsQuantityUnit[i].value;

    var data = {};

    data["userID"] = 1;
    data["productDescriptionList"] = inputList;
    data["recipeGroup"] = $("#recipeGroupInput").val();
    data["quantity"] = inputQuantity;
    data["quantityUnit"] = inputQuantityUnit;
    data["verified"] = 0;

    console.log(data);


    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        url: "/diary-entry/add-new-recipe",
        data: JSON.stringify(data), // Note it is important
        success :function(response) {
            console.log(response);
        }
    });



});


function getMeasurementUnit(value) {
    $.ajax({
        type: 'POST',
        url: '/diary-entry/product-measurement',
        dataType: 'json',
        data: 'productDescription=' + $('#productDescription').val(),
        success: function (response) {
            $("#unit").val(response.productUnit)

        }

    })
}

function getHistoryItems() {
    $.ajax({
        url: '/occurences',
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var history_data = '';
            for (var i = 0; i < data.length; i++) {
                obj = data[i];
                history_data += '<tr>';
                history_data += '<th scope="row"></th>';
                history_data += '<td>'+obj.product_id+'</td>';
                history_data+= '<td>'+obj.productDescription+'</td>';
                history_data += '</tr>';

            }
            $("#historyTable").append(history_data);
        }
    });


}

function getDescriptions() {

    descriptions = [];
    $.ajax({
        url: '/product-description',
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                descriptions.push(obj)
            }
        }
    });
    return descriptions
}

function getTodaysDiaryEntries(diaryTable) {
    $.ajax({
        url: '/product-entries-by-date',
        dataType: 'json',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var obj = response[i];
                diaryTable.row.add([
                    obj.id,
                    obj.mealtime,
                    obj.productDescription,
                    obj.quantity + obj.unit,
                    obj.time,
                    obj.description
                 ]).draw( false );
        }
    }

})
}






function autocomplete(inp, arr) {
    /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    inp.addEventListener("input", function(e) {
        var a, b, i, val = this.value;
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if (!val) { return false;}
        currentFocus = -1;
        /*create a DIV element that will contain the items (values):*/
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        /*append the DIV element as a child of the autocomplete container:*/
        this.parentNode.appendChild(a);
        /*for each item in the array...*/
        for (i = 0; i < arr.length; i++) {
            /*check if the item starts with the same letters as the text field value:*/
            if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                /*create a DIV element for each matching element:*/
                b = document.createElement("DIV");
                /*make the matching letters bold:*/
                b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                b.innerHTML += arr[i].substr(val.length);
                /*insert a input field that will hold the current array item's value:*/
                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                /*execute a function when someone clicks on the item value (DIV element):*/
                b.addEventListener("click", function(e) {
                    /*insert the value for the autocomplete text field:*/
                    inp.value = this.getElementsByTagName("input")[0].value;
                    getMeasurementUnit(inp.value)
                    /*close the list of autocompleted values,
                    (or any other open lists of autocompleted values:*/
                    closeAllLists();
                });
                a.appendChild(b);
            }
        }
    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function(e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
            increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
            decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x) x[currentFocus].click();
            }
        }
    });
    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x) return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }
    function removeActive(x) {
        /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }
    function closeAllLists(elmnt) {
        /*close all autocomplete lists in the document,
        except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
            if (elmnt != x[i] && elmnt != inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }
    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}

function openForm() {
    document.getElementById("loginPopup").style.display="block";
}

function closeForm() {
    document.getElementById("loginPopup").style.display= "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    var modal = document.getElementById('loginPopup');
    if (event.target == modal) {
        closeForm();
    }
}

var modalBtns = [...document.querySelectorAll(".button")];
modalBtns.forEach(function(btn){
    btn.onclick = function() {
        var modal = btn.getAttribute('data-modal');
        document.getElementById(modal).style.display = "block";
    }
});

var closeBtns = [...document.querySelectorAll(".close")];
closeBtns.forEach(function(btn){
    btn.onclick = function() {
        var modal = btn.closest('.modal');
        modal.style.display = "none";
    }
});

window.onclick = function(event) {
    if (event.target.className === "modal") {
        event.target.style.display = "none";
    }
}