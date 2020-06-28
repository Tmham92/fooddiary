package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.model.recipe.Recipe;
import nl.bioinf.fooddiary.model.recipe.SingleRecipeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tom Wagenaar
 * date: 11-05-2020
 *
 * Repository that is used to insert new recipe's in the recipe table, get all recipe groups from the recipe table and
 * get current user from the database.
 */
@Repository
public class RecipeDAO implements RecipeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert a single product from a recipe into the database. Use the index to get the specific product out of the
     * list in the recipe object.
     * @param recipe object holding the information about a recipe
     * @param productCode code for a specific product in the recipe
     * @param index used to retrieve the correct data out of the recipe object
     */
    @Override
    public void insertNewRecipe(Recipe recipe, int productCode, int index) {
        String sqlQuery = "insert into recipe (user_id, product_code, recipe_group, quantity, unit, verified) values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, recipe.getUserID(), productCode, recipe.getRecipeGroup(), recipe.getQuantity().get(index), recipe.getQuantityUnit().get(index), recipe.getVerified());
    }

    @Override
    public List<String> getRecipeGroup() {
        String sqlQuery = "select recipe_group from recipe";
        return jdbcTemplate.queryForList(sqlQuery, String.class);
    }

    @Override
    public List<SingleRecipeProduct> getAllRecipes() {
        String sqlQuery = "select * from recipe";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
                new SingleRecipeProduct(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_code"),
                        rs.getString("recipe_group"),
                        rs.getInt("quantity"),
                        rs.getString("unit"),
                        rs.getInt("verified")
                ));
    }
}
