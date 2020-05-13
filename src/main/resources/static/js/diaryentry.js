//author: Hans Zijlstra

$(document).ready(function (elementId) {
    var arr = getDescriptions();
    autocomplete(document.getElementById("productDescription"), arr);

    autocomplete(document.getElementById("productInput"), arr);


    var t = $('#example').DataTable();
    var counter = 1;

    $('#addRow').on( 'click', function () {
        t.row.add( [
            counter +'.1',
            counter +'.2',
            counter +'.3',
            counter +'.4',
        ] ).draw( false );

        counter++;
    } );

    // Automatically add a first row of data
    $('#addRow').click();

    $(function () {
    $("#product-submit").click(function(event){
        // $("productDescription").val()
        console.log("sending")
        event.preventDefault();
        $.post({
            url : "/diary-entry/addtodiary",
            data: $('#product-entry').serialize(),
            success: function (res) {
                console.log(res)
            }
        });
    });
    });


});



// @Author Tom Wagenaar
// Variable used to make the input id's distinct from each other.
var counter = 1;

// Function that makes a new div consisting of the product the uses wants to submit and a remove product button.
function addProductLine() {
    // Get the product the user wants to add to the recipe
    var input = document.getElementById("productInput").value;

    // Make a new div consisting out of a text field with the product the user wants to add to the recipe + a button
    // to remove the product.
    var newDiv = document.createElement('div');
    newDiv.className = "autocomplete";
    newDiv.id = "productInput" + counter;

    newDiv.innerHTML ="<input id = 'productInput"+counter+"' type='text' placeholder='"+input+"' name='recipeInput[]' value='"+input+"' style='width: 83%;' readonly>" +
        "<input type='button' value='-' onclick='removeProductLine(this)' style='width: 15%;margin-left: 5px'>";

    document.getElementById('dyanamicProductInput[0]').appendChild(newDiv);

    // Clear the product in the input field
    document.getElementById("productInput").value = "";

    counter++;
    console.log("Added a new product input field in the recipe form.")
}

// @Author Tom Wagenaar
// Function that removes the current product and decreases the counter.
function removeProductLine(btn) {
    btn.parentNode.remove();
    counter--;
    console.log("Removed a product input field in the recipe form.")
}


$("#recipeSubmit").click(function (event) {
    console.log("Sending info");
    event.preventDefault();

    var inputList = [];

    var inputFields = document.getElementsByName("recipeInput[]");

    for (var i = 0; i < inputFields.length; i++)
        inputList[i] = inputFields[i].value;

    var data = {};

    data["userID"] = 2;
    data["productDescriptionList"] = inputList;
    data["recipeGroup"] = $("#recipeGroupInput").val();
    data["quantity"] = $("#recipeGroupQuantityInput").val();
    data["verified"] = 0;

    console.log(JSON.stringify(data));

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



function getDescriptions() {

    descriptions = [];
    $.ajax({
        url: '/product-description',
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                descriptions.push(obj.descriptionDutch)

            }
        }
    });
    return descriptions
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