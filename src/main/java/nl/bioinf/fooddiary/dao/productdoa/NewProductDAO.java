package nl.bioinf.fooddiary.dao.productdoa;

import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class NewProductDAO implements IProductDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewProductDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<NewProduct> getAllNewProducts() {
        String sql = "select id, description from unverified_product_entry";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public NewProduct getNewProduct(Integer id, String description) {
        String sql = "select id, discription from unverified_product_entry where id = ? and description = ?";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, id, description);
        return newProduct;
    }

    public void addNewProduct(NewProduct newProduct) {
        String sql = "INSERT INTO unverified_product_entry " +
                "(user_id, date, time_of_day, mealtime, description values (?,?,?,?,?,?);";
        jdbcTemplate.update(sql, newProduct.getUser_id(), newProduct.getDate(), newProduct.getTime_of_day(),
                newProduct.getMealtime(), newProduct.getDate());
    }

    public NewProduct getNewProductById(int newProductId) {
        String sql = "SELECT id, description FROM unverified_product_entry WHERE id = ?";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, newProductId);
        return newProduct;
    }

    public void deleteNewProduct(int newProductId) {
        String sql = "DELETE FROM unverified_product_entry WHERE id = ?";
        jdbcTemplate.update(sql, newProductId);
    }

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
