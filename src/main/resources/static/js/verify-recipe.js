// Author: Tom Wagenaar
$(document).ready(function () {
    var userLang = navigator.language || navigator.userLanguage;
    
    // Make the verify-recipes-table into a JQuery data table, furthermore assign it to a variable.
    const verifyRecipeTable = $('#verify-recipes-table').DataTable();
    retrieveRecipeData(verifyRecipeTable, userLang);


});

// Function that fills the recipe data table with data.
function retrieveRecipeData(verifyRecipeTable, userLang) {
    $.ajax({
        url: "/verify-recipe-by-admin/fill-datatable",
        dataType: "json",
        success: function (recipeData) {

            // Receive a list of CombinedRecipeProducts objects, for each object, get the recipe name and the list
            // of products. Based on the language get the product description for each product.
            for (var index = 0; index < recipeData.length; index++) {
                const productDataList = recipeData[index].productTableData;
                let productDescriptionData = [];

                if (userLang === "nl") {
                    for (var indexTwo = 0; indexTwo < productDataList.length; indexTwo++) {
                        productDescriptionData.push(productDataList[indexTwo].productDescription.descriptionDutch);
                    }
                } else {
                    for (var indexTwo = 0; indexTwo < productDataList.length; indexTwo++) {
                        productDescriptionData.push(productDataList[indexTwo].productDescription.descriptionEnglish);
                    }
                }

            verifyRecipeTable.row.add([
                recipeData[index].recipeName,
                productDescriptionData

            ]).draw(false);
            }
        }
    })
}
