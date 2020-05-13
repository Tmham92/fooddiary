package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.model.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Tom Wagenaar
 * date: 11-05-2020
 *
 */
@Repository
public class RecipeDAO implements RecipeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertNewRecipe(Recipe recipe, int productCode) {
        String sqlQuery = "insert into recipe (user_id, product_code, recipe_group, quantity, verified) values (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, recipe.getUserID(), productCode, recipe.getRecipeGroup(), recipe.getQuantity(), recipe.getVerified());
    }
}
