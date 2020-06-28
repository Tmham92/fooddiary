package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.recipe.CombinedRecipeProducts;
import nl.bioinf.fooddiary.model.recipe.SingleRecipeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author Tom Wagenaar
 * Date: 05-06-2020
 *
 * Service class that is used to process the recipe data, this involves retrieving the recipe data from the recipe table,
 * retrieving the correspondig product data from the product table and creating a list of CombinedRecipeProduducts objects
 * that contain all the data.
 */
@Service
public class VerifyRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CombinedRecipeProducts> processRecipeData() {
        // Get the recipe data from the database.
        List<SingleRecipeProduct> recipeTableData = recipeRepository.getAllRecipes();

        // Get the products from the product table that correspond to the products in the recipe table.
        List<Product> productTableData = getMatchingProducts(recipeTableData);

        List<CombinedRecipeProducts> combinedRecipeProductsList = createCombinedRecipeObjects(recipeTableData, productTableData);
        return combinedRecipeProductsList;
    }

    /**
     * Receive a list containing all the products from a single recipe, then for each product retrieve the corresponding
     * product from the product table.
     * @param valueList List containing the products for a single recipe.
     * @return productTableData, a list of all the products out of the product table that correspond to the products
     * from a single recipe.
     */
    public List<Product> getMatchingProducts(List<SingleRecipeProduct> valueList) {
        // Get the products that correspond to the product in the recipes.
        List<Product> productTableData = new ArrayList<>();

        // For each singleRecipeProduct, retrieve the corresponding product from the product table.
        for (SingleRecipeProduct singleRecipeProduct: valueList) {
            Product product = productRepository.getSpecificProductByProductCode(singleRecipeProduct.getProductCode());
            productTableData.add(product);
        }

        return productTableData;
    }

    /**
     * Create a list of CombinedRecipeProducts, a CombinedRecipeProducts consists out of a recipe name, a list
     * containing products in that recipe and a list of products from the product table that correspond to those
     * products from the recipe. A for loop is used to get the recipes with the same name, thereby also getting the
     * corresponding products from the other list using the index.
     * @param recipeTableData List of SingleRecipeProducts
     * @param productTableData List of Products
     */
    public List<CombinedRecipeProducts> createCombinedRecipeObjects(List<SingleRecipeProduct> recipeTableData, List<Product> productTableData) {
        List<CombinedRecipeProducts> combinedRecipeProductsList = new ArrayList<>();

        int startPos = 0;
        int stopPos = 1;

        String recipeName = "";


        for (int index = 0; index < recipeTableData.size(); index++) {

            if (index == 0) {
                // Get the recipe group name from the first SingleRecipeProduct object in the list and assign it.
                recipeName = recipeTableData.get(index).getRecipeGroup();

            } else {
                if (recipeTableData.size() - 1 == index) {
                    // Whenever the last index is reached, create a new CombinedRecipeProduct using the start and stop
                    // positions. By the stop position is 2 added, because sublist get the objects up to a specific number.
                    CombinedRecipeProducts combinedRecipeProducts = new CombinedRecipeProducts(recipeName, recipeTableData.subList(startPos, stopPos + 2), productTableData.subList(startPos, stopPos + 2));
                    combinedRecipeProductsList.add(combinedRecipeProducts);

                } else if (recipeTableData.get(index).getRecipeGroup().equals(recipeName)) {
                    // If the recipeName variable is the same as the recipe name from the SingleRecipeProduct object then
                    // assign the index value to the stop position variable.
                    stopPos = index;
                } else {
                    // If all the other statements don't pass make a new CombinedRecipeProducts and add it to the list.
                    CombinedRecipeProducts combinedRecipeProducts = new CombinedRecipeProducts(recipeName, recipeTableData.subList(startPos, stopPos + 1), productTableData.subList(startPos, stopPos + 1));
                    combinedRecipeProductsList.add(combinedRecipeProducts);

                    // Overwrite the current recipeName variable, because by the next iteration the previous recipeName
                    // is already used to create a new CombinedRecipeProducts object.
                    recipeName = recipeTableData.get(index).getRecipeGroup();

                    // Assign the value of the index to the start and stop position variables.
                    startPos = index;
                    stopPos = index;
                }
            }
        }

        return combinedRecipeProductsList;
    }


    // NOTE!! This is code that I used to work with, this way is devious and not OOP orientated. Don't want to remove it
    // because a lot of time is put in this.

//    /**
//     * Method that processes the recipe data from the recipe table, furthermore calling different methods that process
//     * or adjust the data. Start off with retrieving all products in the recipe table, then creating a HashMap that
//     * consists out of the name of the recipe and the products that correspond to the recipe, at last call the method
//     * that creates a LinkedHashMap consisting out of the name of the recipe and a HashMap that contains the products
//     * in that recipe and the products from the product table that correspond to the products in the recipe table.
//     * @return LinkedHashMap<String, HashMap<List<SingleRecipeProduct>, List<Product>>>
//     */
//    public LinkedHashMap<String, HashMap<List<SingleRecipeProduct>, List<Product>>> processRecipeData() {
//        // Get the recipe data from the database.
//        List<SingleRecipeProduct> recipeTableData = recipeRepository.getAllRecipes();
//
//        // Transform the recipe data into a LinkedHashMap
//        HashMap<String, List<SingleRecipeProduct>> recipeDataMap = createRecipeMap(recipeTableData);
//
//        // Create another LinkedHashMap that consists out of a recipe name and a object containing a product and the
//        // corresponding recipe product from the recipe table.
//        LinkedHashMap<String, HashMap<List<SingleRecipeProduct>, List<Product>>> completeRecipeDataMap = createFinalRecipeMap(recipeDataMap);
//        return completeRecipeDataMap;
//    }
//
//    /**
//     * Create a hash map that consists out of a recipe name as a key and a list containing the products in that
//     * recipe as the value.
//     * @param recipeTableData: List containing all the rows in the recipe table in the database.
//     * @return recipeDataMap (LinkedHashMap<String, List<SingleRecipeProduct>>).
//     */
//    public HashMap<String, List<SingleRecipeProduct>> createRecipeMap(List<SingleRecipeProduct> recipeTableData) {
//        HashMap<String, List<SingleRecipeProduct>> recipeDataMap = new HashMap<>();
//
//        for (SingleRecipeProduct singleRecipeProduct: recipeTableData) {
//            if (recipeDataMap.isEmpty()) {
//                // Add a SingleRecipeProduct object to a newly created list.
//                List<SingleRecipeProduct> newRecipeList = new ArrayList<>();
//                newRecipeList.add(singleRecipeProduct);
//
//                // Fill the empty map with a recipe name and a list that contains one SingleRecipeProduct.
//                recipeDataMap.put(singleRecipeProduct.getRecipeGroup(), newRecipeList);
//            } else if (recipeDataMap.containsKey(singleRecipeProduct.getRecipeGroup())) {
//                // If the hash map contains a key that correspond to the recipe name from the product, add that
//                // product to the list.
//                List<SingleRecipeProduct> currentRecipe = recipeDataMap.get(singleRecipeProduct.getRecipeGroup());
//                currentRecipe.add(singleRecipeProduct);
//            } else {
//                // Add a SingleRecipeProduct object to a newly created list.
//                List<SingleRecipeProduct> newRecipeList = new ArrayList<>();
//                newRecipeList.add(singleRecipeProduct);
//
//                // Fill the empty map with a recipe name and a list that contains one SingleRecipeProduct.
//                recipeDataMap.put(singleRecipeProduct.getRecipeGroup(), newRecipeList);
//            }
//        }
//
//        return recipeDataMap;
//    }
//
//    /**
//     * Create a linkedHashMap that act as the outer layer of the recipe data table. This LinkedHashMap has a key that
//     * is the name of a recipe and a HashMap that is the value of that key. The HashMap in the LinkedHashMap acts as
//     * the inner layer. This HashMap has a list of singleRecipeProducts as key and a list of Products as value. Those
//     * products correspond to the singleRecipeProducts.
//     *
//
//     * @param recipeDataMap
//     */
//    public LinkedHashMap<String, HashMap<List<SingleRecipeProduct>, List<Product>>> createFinalRecipeMap(HashMap<String, List<SingleRecipeProduct>> recipeDataMap) {
//
//        // Create a LinkedHashMap that acts as the outer layer.
//        LinkedHashMap<String, HashMap<List<SingleRecipeProduct>, List<Product>>> recipeDataOuterMap = new LinkedHashMap<>();
//
//        // For every key in the recipeDataMap, make a new HashMap, retrieve the products that correspond to the products
//        // in the recipe and add those products as a list and the products from the recipe to the HashMap.
//        for (Map.Entry<String, List<SingleRecipeProduct>> recipe: recipeDataMap.entrySet()) {
//
//            // Create a empty HashMap that acts as the inner layer consisting out of two lists.
//            HashMap<List<SingleRecipeProduct>, List<Product>> recipeDataInnerMap = new HashMap<>();
//
//            // Retrieve the products that correspond to the products in the recipe.
//            List<Product> productList = getMatchingProducts(recipe.getValue());
//
//            // Put the recipe products and the corresponding products from the product table in the HashMap.
//            recipeDataInnerMap.put(recipe.getValue(), productList);
//
//            // Add the inner HashMap to the outer HashMap.
//            recipeDataOuterMap.put(recipe.getKey(), recipeDataInnerMap);
//        }
//
//        return recipeDataOuterMap;
//    }
}
