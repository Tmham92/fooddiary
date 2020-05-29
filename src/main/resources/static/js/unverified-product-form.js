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

$(document).ready( function() {
    var form = document.getElementById("verify-product-form");
    form.find('select:first').change( function() {
        $.ajax( {
            type: "POST",
            url: form.attr( 'action' ),
            data: form.serialize(),
            success: function( response ) {
                console.log( response );
            }
        } );
    } );
} );
