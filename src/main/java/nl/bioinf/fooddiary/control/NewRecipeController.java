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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

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
    public @ResponseBody String createNewRecipe(@RequestBody @Valid Recipe recipe, BindingResult result) {

        logger.info("/diary-entry/add-new-recipe has been called, receiving new recipe.");

        if (result.hasErrors()) {
            logger.info("Recipe contains errors, returning errors!");
            return "/diary-entry/add-new-recipe";
        } else {

            logger.info("Inserting a new recipe in the recipe table");

            for (int index = 0; index < recipe.getProductDescriptionList().size(); index++) {
                String productDescription = recipe.getProductDescriptionList().get(index);

                logger.info("Retrieving product that contains the description: " + productDescription);
                Product product = productRepository.getSpecificProduct(productDescription);

                logger.info("Inserting linking table recipe with a new product: " + productDescription);
                recipeRepository.insertNewRecipe(recipe, product.getCode(), index);
            }
        }


        return "diary-entry";

    }

}
