package nl.bioinf.fooddiary.dao.newproductdao;

import nl.bioinf.fooddiary.dao.jdbc.IProductDAO;
import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class NewProductDAO implements IProductDAO {

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

    public NewProduct getNewProduct(Integer id, String description, Date date, Time time_of_day, String mealtime, double quantity) {
        String sql = "select id, description, date, time_of_day, mealtime, quantity from unverified_product_entry where id = ? and description = ?";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, id, description, date, time_of_day, mealtime, quantity);
        return newProduct;
    }

    public void addNewProduct(NewProduct newProduct) {
        String sql = "INSERT INTO unverified_product_entry " +
                "(user_id, date, time_of_day, mealtime, description, quantity values (?,?,?,?,?,?,?);";
        jdbcTemplate.update(sql, newProduct.getUser_id(), newProduct.getDate(), newProduct.getTime_of_day(),
                newProduct.getMealtime(), newProduct.getDate(), newProduct.getQuantity());
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
