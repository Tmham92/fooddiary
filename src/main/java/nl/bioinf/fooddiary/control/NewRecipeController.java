package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.recipe.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tom Wagenaar
 * date: 12-05-2020
 *
 *
 */
@Controller
public class NewRecipeController {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    // TODO: Een unique code verzinnen die elke keer aangemaakt wordt voor een nieuw recept.
    @RequestMapping(value = "/diary-entry/add-new-recipe", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Recipe createNewRecipe(@RequestBody Recipe recipe) {

        logger.info("/diary-entry/add-new-recipe has been called, receiving new recipe.");
        logger.info("Inserting a new recipe in the recipe table");

        for (String productDescription: recipe.getProductDescriptionList()) {
            System.out.println(productDescription);

            logger.info("Retrieving product that contains the description: " + productDescription);
            Product product = productRepository.getSpecificProduct(productDescription);

            logger.info("Inserting linking table recipe with a new product: " + productDescription);
            recipeRepository.insertNewRecipe(recipe, product.getCode());
        }

        return recipe;

    }

//    @PostMapping(value = "/diary-entry/add-new-recipe")
//    @ResponseBody
//    public String createNewRecipe(@RequestParam int userID, @RequestParam JSONArray productDescriptionList,
//                                  @RequestParam String recipeGroup, @RequestParam String quantity, @RequestParam int verified) {
//
//        logger.info("/diary-entry/add-new-recipe has been called, inserting receiving new recipe.");
//
//        Recipe recipe = Recipe.builder(userID, productDescriptionList, recipeGroup, quantity, verified)
//                .build();
//
//        for (String productID:productDescriptionList) {
//            recipeRepository.insertNewRecipe(recipe, Integer.parseInt(productID));
//            logger.info("Linking table recipe has been updated with recipe: " + recipe.getRecipeGroup() + " containing productID: " + productID);
//        }
//
//
//        return "redirect:/add-new-recipe";
//
//    }

}
