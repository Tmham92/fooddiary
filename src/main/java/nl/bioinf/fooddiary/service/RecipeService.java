package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.recipe.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    public void insertRecipe(Recipe recipe) {
        logger.info("Inserting a new recipe in the recipe table");

        int userId = recipeRepository.getCurrentUser();
        logger.info("retrieving current user:" + userId);
        recipe.setUserID(userId);

        for (int index = 0; index < recipe.getProductDescriptionList().size(); index++) {
            String productDescription = recipe.getProductDescriptionList().get(index);

            logger.info("Retrieving product that contains the description: " + productDescription);
            Product product = productRepository.getSpecificProduct(productDescription);

            logger.info("Inserting linking table recipe with a new product: " + productDescription);
            recipeRepository.insertNewRecipe(recipe, product.getCode(), index);
        }

        logger.info("New recipe: " + recipe.getRecipeGroup() + " inserted into the database.");
    }

    public List<String> getRecipeGroups() {
        List<String> recipeGroupList = recipeRepository.getRecipeGroup();
        return recipeGroupList;
    }
}
