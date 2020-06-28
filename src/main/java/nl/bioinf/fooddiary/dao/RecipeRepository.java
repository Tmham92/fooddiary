package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.recipe.Recipe;

import java.util.List;

/**
 * @author Tom Wagenaar
 * date: 11-05-2020
 *
 * Interface that supports the RecipeDAO class, this interface is used to insert new recipes in the recipe table, get
 * all the recipe names from the recipe table and get the current user.
 *
 */
public interface RecipeRepository {

    void insertNewRecipe(Recipe recipe, int productCode, int index);

    List<String> getRecipeGroup();

    int getCurrentUser();
}
