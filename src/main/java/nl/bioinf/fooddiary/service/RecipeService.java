package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.dao.UserRepository;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.recipe.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * Service class that acts as a layer that separates the controller class and the connected DAO classes. This service
 * class receives form data from the recipe controller, retrieves the current user and passes the data on to the
 * corresponding repository. This service is also used to call the methods in the ProductDAO that retrieve the all the
 * recipe groups in the recipe table.
 */
@Service
public class RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void insertRecipe(Recipe recipe) {
        logger.info("RecipeService: receives recipe object.");

        logger.info("Retrieving current user and setting the recipe userID.");
        int userId = getCurrentUserID();
        recipe.setUserID(userId);

        boolean checkedRecipeBool = checkRecipe(recipe, getRecipeGroups());

        if (checkedRecipeBool) {
            for (int index = 0; index < recipe.getProductDescriptionList().size(); index++) {
                String productDescription = recipe.getProductDescriptionList().get(index);

                logger.info("Retrieving product that contains the description: " + productDescription);
                Product product = productRepository.getSpecificProductByDescription(productDescription);

                logger.info("Inserting linking table recipe with a new product: " + productDescription);
                recipeRepository.insertNewRecipe(recipe, product.getCode(), index);
            }

            logger.info("New recipe: " + recipe.getRecipeGroup() + " inserted into the database.");
        } else {

            logger.info("Recipe name is already in the database, returning error!");
        }
    }

    /**
     * Method that retrieves all the recipe names from the recipe table as a list.
     * @return recipeGroupList, list of all the recipe names.
     */
    public List<String> getRecipeGroups() {
        logger.info("RecipeService: retrieving all the recipe names from the database.");
        List<String> recipeGroupList = recipeRepository.getRecipeGroup();
        return recipeGroupList;
    }

    private int getCurrentUserID() {
        int userId = userRepository.getCurrentUser();
        logger.info("RecipeService: retrieving current user:" + userId);
        return userId;
    }

    private boolean checkRecipe(Recipe recipe, List<String> recipeNamesList) {
        return !recipeNamesList.contains(recipe.getRecipeGroup());
    }
}
