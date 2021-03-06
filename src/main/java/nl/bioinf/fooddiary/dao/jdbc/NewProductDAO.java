package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.NewProductRepository;
import nl.bioinf.fooddiary.model.newproduct.NewProduct;
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
public class NewProductDAO implements NewProductRepository {

    private RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Method that creates a list from all new products in the database.
     * @return List object with NewProducts
     */
    @Override
    public List<NewProduct> getAllNewProducts() {
        String sql = "select id, user_id, date, time_of_day, mealtime, description, quantity from unverified_product_entry";
        List<NewProduct> newProductList = jdbcTemplate.query(sql, rowMapper);
        return newProductList;
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
        String sql = "select id, description, date, time_of_day, mealtime, quantity from unverified_product_entry where id = ?";
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
        jdbcTemplate.update(sql, user_id, newProduct.getDate(), newProduct.getTimeOfDay(),
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
     * @param id (int)
     * @return (NewProduct object)
     */
    public NewProduct getNewProductById(int id) {
        String sql = "SELECT id, user_id, description, date, time_of_day, mealtime, quantity FROM unverified_product_entry WHERE id = ?";
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return newProduct;
    }

    /**
     * Method that deletes a NewProduct object from the database based on their corresponding ID value.
     * @param newProductId (int)
     */
    public void deleteNewProduct(int newProductId) {
        String sql = "DELETE FROM unverified_product_picture WHERE unverified_product_id = ?";
        jdbcTemplate.update(sql, newProductId);
        sql = "DELETE FROM unverified_product_entry WHERE id = ?";
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
    /**
     *  Method that inserts the New Product Picture Location into the database.
     *  The newProduct ID that is associated with the picture is retrieved from
     *  the unverified_product_entry table.
     *  The highest ID number is used because this is the last product entry due to auto incrementation.
     * @param pictureLocation
     */


    public void addNewProductPictureLocation(String pictureLocation) {

        int unverified_product_id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM unverified_product_entry", Integer.class);
        System.out.println(unverified_product_id);
        String sql = "INSERT INTO unverified_product_picture " +
                "(unverified_product_id,  unverified_product_picture_location) VALUES (?,?);";
        jdbcTemplate.update(sql, unverified_product_id, pictureLocation);
    }

}
