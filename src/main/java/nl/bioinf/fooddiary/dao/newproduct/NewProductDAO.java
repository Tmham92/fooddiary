package nl.bioinf.fooddiary.dao.newproduct;

import nl.bioinf.fooddiary.dao.jdbc.IProductDAO;
import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * @author Tobias Ham
 * @version 0.0.1
 * date 17-03-2020
 *
 * Class which funtions as a Data Accesss Object to inject or retrieve data from the database.
 */
@Repository
public class NewProductDAO implements IProductDAO {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<NewProduct> rowMapper = new NewProductRowMapper();

    @Autowired
    public NewProductDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Method that creates a list from all new products in the database.
     * @return List object with NewProducts
     */
    @Override
    public List<NewProduct> getAllNewProducts() {
        String sql = "select id, user_id, date, time_of_day, mealtime, description, quantity from unverified_product_entry";
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * Method that retrieves a newProduct object from the database
     * @param id (int)
     * @param description (String)
     * @param date (date)
     * @param time_of_day (Time)
     * @param mealtime (String)
     * @param quantity (double)
     * @return NewProduct object
     */
    public NewProduct getNewProduct(Integer id, String description, Date date, Time time_of_day, String mealtime, double quantity) {
        String sql = "select id, description, date, time_of_day, mealtime, quantity from unverified_product_entry where id = ? and description = ?";
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, id, description, date, time_of_day, mealtime, quantity);
        return newProduct;
    }

    /**
     * Method that inserts a new Product object into the database, taking the required NewProduct object as argument.
     * @param newProduct (newProduct object)
     */
    public void addNewProduct(NewProduct newProduct) {
        int user_id = getUserIdFromUserCode(newProduct);
        String sql = "INSERT INTO unverified_product_entry " +
                "(user_id, date, time_of_day, mealtime, description, quantity) values (?,?,?,?,?,?);";
        jdbcTemplate.update(sql, user_id, newProduct.getDate(), newProduct.getTime_of_day(),
                newProduct.getMealtime(), newProduct.getDescription(), newProduct.getQuantity());
    }


    /**
     * Method that retrieves a user_id based on the active user.
     * The user_id is required to complete a NewProduct object.
     * @return (int user_id)
     * @param newProduct
     */
    public int getUserIdFromUserCode(NewProduct newProduct) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object[] param = new Object[] {
                auth.getName()
        };
        String sqlGetId = "SELECT id FROM user WHERE user_code = ?";
        int user_id = jdbcTemplate.queryForObject(sqlGetId, param, Integer.class);
        return user_id;
    }

    /**
     * Method that retrieves NewProduct object based on their corresponding ID value.
     * @param newProductId (int)
     * @return (NewProduct object)
     */
    public NewProduct getNewProductById(int newProductId) {
        String sql = "SELECT id, description FROM unverified_product_entry WHERE id = ?";
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, newProductId);
        return newProduct;
    }

    /**
     * Method that deletes a NewProduct object from the database based on their corresponding ID value.
     * @param newProductId (int)
     */
    public void deleteNewProduct(int newProductId) {
        String sql = "DELETE FROM unverified_product_entry WHERE id = ?";
        jdbcTemplate.update(sql, newProductId);
    }

    /**
     * Method that checks whether a NewProduct object already exists, using the required newProductId nad
     * returning a boolean value.
     * @param newProductId (int)
     * @return (boolean)
     */
    public boolean newProductExists(int newProductId) {
        String sql = "SELECT count FROM unverified_product_entry WHERE id = ?";
        List<Integer> count = jdbcTemplate.queryForList(sql, Integer.class, newProductId);
        if(count.get(0) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
