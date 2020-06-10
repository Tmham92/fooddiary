package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.model.recipe.Recipe;
import nl.bioinf.fooddiary.model.recipe.SingleRecipeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /**
     * Get the current user that is logged in the application and check if it is authenticated. Then get the id of the
     * user and return it.
     * @return Id of the current user.
     */
    @Override
    public int getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object[] param = new Object[] {
                auth.getName()
        };
        String sqlGetId = "select id from user where user_code = ?";
        int user_id = jdbcTemplate.queryForObject(sqlGetId, param, Integer.class);
        return user_id;
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
