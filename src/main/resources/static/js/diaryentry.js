//author: Hans Zijlstra

$(document).ready(function() {
    var userLang = navigator.language || navigator.userLanguage;
    console.log(userLang)
    var diaryTable;
    var descriptions = getDescriptions();

    var recipeGroupList = getRecipeGroup();

    getHistoryItems();

    //Select table rows for history items

    $("#historyTable tbody").on('click', 'tr', function (e) {
        var $row = jQuery(this).closest('tr');
        var $columns = $row.find('td');

        $columns.addClass('row-highlight');
        var values = [];

        jQuery.each($columns, function(i, item) {
            values.push(item.innerHTML)
        });
        document.getElementById("historyMealtime").value = values[0]
        document.getElementById("historyProductDescription").value = values[1]
        document.getElementById("historyUnit").value = values[2]
    });



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

    function addToDiary(data) {
        $.ajax({
            url : "/diary-entry/addtodiary",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
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


        if (!descriptions.includes(data.productDescription)) {
            var message = '';
            if (userLang === "nl") {
                message = "Het ingevoerde product komt niet voor in onze database"
            } else {
                message = "The product you entered does not appear in our database"

            }
            document.getElementById("productDescription").setCustomValidity(message);
        } else {
            addToDiary(data)
        }

    });

    $("#history-entry").submit(function(event){
        event.preventDefault();
        var data = {};
        data["productDescription"] = $("#historyProductDescription").val();
        data["mealtime"] = $("#historyMealtime").val();
        data["quantity"] = $("#historyQuantity").val();
        data["unit"] = $("#historyUnit").val();
        data["date"] = $("#historyDate").val();
        data["time"] = $("#historyTime").val();
        data["description"] = $("#historyDescription").val();

        if (!descriptions.includes(data.productDescription)) {
            var message = '';
            if (userLang === "nl") {
                message = "Het ingevoerde product komt niet voor in onze database"
            } else {
                message = "The product you entered does not appear in our database"

            }
            document.getElementById("historyProductDescription").setCustomValidity(message);
        } else {
            addToDiary(data)
        }

    });




    // @Author Tom Wagenaar
    // Variable used to make the input id's distinct from each other.
    var counter = 1;

    // Function that makes a new div consisting of the product the user wants to submit and a remove product button.
    $('#recipeAddProduct').click(function() {

        // Retrieve the recipe name, the product id, the product quantity and the quantity unit from the recipe form.
        var recipeGroup = document.getElementById("recipeGroupInput").value;
        var inputProduct = document.getElementById("productInput").value;
        var inputProductQuantity = document.getElementById("productQuantity").value;
        var inputProductQuantityUnit = document.getElementById("productQuantityUnit").value;

        inputProduct = inputProduct.trim();
        inputProductQuantity = inputProductQuantity.trim();

        if (!checkRecipeInput(recipeGroup, recipeGroupList, inputProduct, inputProductQuantity)) {
            console.log("Recipe product input isn't correct!")
        } else {
            // Create a variable that contains the users input.
            var recipeProduct = inputProductQuantity + " " + inputProductQuantityUnit + " " + inputProduct;

            // Create a new div that has a inner html that consists out of a input field with the users input and a button
            // to remove the product from the recipe. At last append the div to the product input div in the diary-entry file
            var addedProductDiv = document.createElement('div');
            addedProductDiv.id = "productInput" + counter;

            addedProductDiv.innerHTML = "<input id='recipeProductInput' type='text' placeholder='" + recipeProduct + "' readonly>" +
                "<input id='recipeRemoveButton' type='button' value='-' onclick='removeProductLine(this)'>";

            document.getElementById('dyanamicProductInput[0]').appendChild(addedProductDiv);

            // Create a new div that has is not shown to the user. This div is made so the input data easily can be passed
            // on to the back-end. The inner html consists out of tree input fields that hold the users input data.
            var hiddenProductDataDiv = document.createElement('div');
            hiddenProductDataDiv.id = "hiddenProductInput" + counter;

            hiddenProductDataDiv.innerHTML =
                "<input id = 'productInput" + counter + "' type='text' name='recipeInput[]' value='" + inputProduct + "' hidden>" +
                "<input id='productInputQuantity" + counter + "' type='text' name='recipeInputQuantity[]' value='" + inputProductQuantity + "' hidden>" +
                "<input id='inputProductQuantityUnit" + counter + "' type='text' name='recipeInputQuantityUnit[]' value='" + inputProductQuantityUnit + "' hidden>";

            document.getElementById('dyanamicProductInput[0]').appendChild(hiddenProductDataDiv);

            // After the users adds the product to the recipe remove the values.
            document.getElementById("productInput").value = "";
            document.getElementById("productQuantity").value = "";
            document.getElementById("productQuantityUnit").value = "";

            counter++;
            console.log("Added a new product input field in the recipe form.")
        }
    });

    // @Author Tom Wagenaar
    // Whenever the submit button is clicked in the recipe form, this function will get the data from the form. This data
    // is then checked, processed and passed on to the back-end using ajax.
    $("#recipeSubmit").click(function (event) {

        event.preventDefault();

        // Make three lists that will hold the data from the form.
        var inputProductList = [];
        var inputProductQuantityList = [];
        var inputProductQuantityUnitList = [];

        // Get the product, product quantity and product quantity unit data from the form.
        var formProductInput = document.getElementsByName("recipeInput[]");
        var formProductQuantity = document.getElementsByName("recipeInputQuantity[]");
        var formProductQuantityUnit = document.getElementsByName("recipeInputQuantityUnit[]");

        if (checkProductLength(formProductInput)) {
            let index;

            // Three for loops that iterate over the data from the form and add it to corresponding lists.
            for (index = 0; index < formProductInput.length; index++)
                inputProductList[index] = formProductInput[index].value;

            for (index = 0; index < formProductQuantity.length; index++)
                inputProductQuantityList[index] = formProductQuantity[index].value;

            for (index = 0; index < formProductQuantityUnit.length; index++)
                inputProductQuantityUnitList[index] = formProductQuantityUnit[index].value;

            var formData = {};

            // Add a userId that is in the database to to the formData variable. This userId will be changed to the current
            // user in the back-end, furthermore add the three lists, the recipe group name and a unverified tag to the variable.
            formData["userID"] = 10;
            formData["productDescriptionList"] = inputProductList;
            formData["recipeGroup"] = $("#recipeGroupInput").val();
            formData["quantity"] = inputProductQuantityList;
            formData["quantityUnit"] = inputProductQuantityUnitList;
            formData["verified"] = 0;

            console.log(formData);

            // Do a ajax call, that sends the form data to the RecipeController in the back-end.
            $.ajax({
                type: "POST",
                contentType : 'application/json; charset=utf-8',
                url: "/diary-entry/add-new-recipe",
                dataType : 'json',
                data: JSON.stringify(formData), // Note it is important
                success :function() {

                }
            });


            // $('#addNewRecipe').modal('hide');
            // $('#addNewRecipe').modal('toggle');
        }
    });
});

// @Author Tom Wagenaar
// Function that removes the current product and decreases the counter by one.
function removeProductLine(btn) {
    btn.parentNode.remove();
    window.counter--;
    console.log("Removed a product input field in the recipe form.")
}

// @Author Tom Wagenaar
// function that checks recipe group, the product and the product quantity in the recipe form.
function checkRecipeInput(recipeGroup, recipeGroupList, inputProduct, inputProductQuantity) {

    // Give an error whenever the recipe name is already in the database
    if (recipeGroupList.includes(recipeGroup)) {
        document.getElementById("errorMessageDiv").innerHTML = "This recipe name: " + recipeGroup + " is already in the database please use another name!";
        document.getElementById("errorMessageDiv").style.border = "solid";
        console.log("recipe group is already in the database");
        return false;

    // Give an error message whenever the product isn't in the database.
    } else if (!window.descriptions.includes(inputProduct)) {
        document.getElementById("errorMessageDiv").innerHTML = "This product: " + inputProduct + " isn't in the database!";
        document.getElementById("errorMessageDiv").style.border = "solid";
        return false;

    // Give an error message whenever the quantity isn't a integer.
    } else if (!/^\d+$/.test(inputProductQuantity)) {
        document.getElementById("errorMessageDiv").innerHTML = "Product Quantity must be a positive number!";
        document.getElementById("errorMessageDiv").style.border = "solid";
        return false;
    }

    return true;
}

//@author Tom Wagenaar
// Do A check whenever the user submits the data, do a check on whenever there a are at least two products added to
// the recipe.
function checkProductLength(formProductInput) {

    // Give a error message whenever the recipe has less then two products.
    if (formProductInput.length < 2) {
        document.getElementById("errorMessageDiv").innerHTML = "Please add more then two products to the recipe!";
        document.getElementById("errorMessageDiv").style.border = "solid";
        return false;
    }

    return true;
}

// @Author Tom Wagenaar
// Retrieve every group name from the database.
function getRecipeGroup() {
    let recipeGroupList = [];

    $.ajax({
        url: '/diary-entry/get-recipe-group',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                recipeGroupList.push(obj)
            }
        }
    });

    return recipeGroupList;
}



function getMeasurementUnit(fieldId, unitId) {
    $.ajax({
        type: 'POST',
        url: '/diary-entry/product-measurement',
        dataType: 'json',
        data: 'productDescription=' + $("#" + fieldId).val(),
        success: function (response) {
            unitId.val(response.productUnit)
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
                history_data += '<td>'+obj.mealtime+'</td>';
                history_data += '<td>'+obj.productDescription+'</td>';
                history_data += '<td>'+obj.meassurementUnit+'</td>';
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

                    // In the API there are two input fields that use the autocomplete function, therefore this if
                    // else statement is used to determine which input field is used. When the user clicks on a product
                    // corresponding input field id and the input field that gets the measurement unit are passed on.
                    if (inp.id === "productDescription") {
                        var unitId = $("#unit");
                        getMeasurementUnit(inp.id, unitId);
                    } else {
                        var recipeId = $("#productQuantityUnit");
                        getMeasurementUnit(inp.id, recipeId);
                    }


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