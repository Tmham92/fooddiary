package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.recipe.Recipe;
import nl.bioinf.fooddiary.service.RecipeService;
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
import java.util.List;

/**
 * @author Tom Wagenaar
 * date: 12-05-2020
 *
 * Controller class that receives data from the recipe form in the data-entry.html file, this data is mapped to a
 * recipe object. When the data is valid this recipe object is passed on to the insertRecipe method in the RecipeService
 * class. This controller class also listens to the /diary-entry/get-recipe-group request, when called all the recipe
 * group names out of the recipe table are returned.
 */
@Controller
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private RecipeService recipeService;

    /**
     * Create a new recipe object and pass that object on to the RecipeService class. Use logger to log any errors.
     * @param recipe Recipe object where the HtppRequest body is mapped to a recipe object.
     * @param result BindingResult object that contains errors whenever the recipe can't be made.
     * @return When everything passes return to the diary-entry page.
     */
    @RequestMapping(value = "/diary-entry/add-new-recipe", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String createNewRecipe(@RequestBody @Valid Recipe recipe, BindingResult result) {

        logger.info("/diary-entry/add-new-recipe has been called, receiving new recipe.");

        if (result.hasErrors()) {
            logger.info("Recipe contains errors, returning errors!");
            logger.info(result.toString());

            return "/diary-entry/add-new-recipe";
        } else {
            logger.info("Recipe has successfully been made and is passed on to the RecipeService class.");
            recipeService.insertRecipe(recipe);
        }

        return "diary-entry";
    }

    /**
     * Method that calls the RecipeService class whenever the user wants to insert a new recipe. All the Recipe names
     * are fetched from the recipe table and returned to the javascript.
     * @return List containing string that represent the recipe names in the recipe table.
     */
    @RequestMapping(value = "/diary-entry/get-recipe-group", method = RequestMethod.GET)
    public @ResponseBody List<String> getRecipeGroup() {
        logger.info("/diary-entry/get-recipe-group called, retrieving all the recipe names from the database!");
        return recipeService.getRecipeGroups();
    }

}
