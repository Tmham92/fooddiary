package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.recipe.Recipe;

/**
 * @author Tom Wagenaar
 * date: 11-05-2020
 *
 */
public interface RecipeRepository {

    void insertNewRecipe(Recipe recipe, int productCode);
}
