package nl.bioinf.fooddiary.dao.newproductdao;

import nl.bioinf.fooddiary.dao.jdbc.IProductDAO;
import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.security.Principal;
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
        String sql = "select id, user_id, date, time_of_day, mealtime, description, quantity from unverified_product_entry";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    public NewProduct getNewProduct(Integer id, String description, Date date, Time time_of_day, String mealtime, double quantity) {
        String sql = "select id, description, date, time_of_day, mealtime, quantity from unverified_product_entry where id = ? and description = ?";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, id, description, date, time_of_day, mealtime, quantity);
        return newProduct;
    }

    // Doet niet maar 1 ding.
    public void addNewProduct(NewProduct newProduct) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Because the newProduct has a String user_id value, which comes from user_code.
        // This value needs te be converted to the corresponding user_id Integer.
        Object[] param = new Object[] {
                auth.getName()
        };
        String sqlGetId = "SELECT id FROM user WHERE user_code = ?";
        int user_id = jdbcTemplate.queryForObject(sqlGetId, param, Integer.class);

        String sql = "INSERT INTO unverified_product_entry " +
                "(user_id, date, time_of_day, mealtime, description, quantity) values (?,?,?,?,?,?);";
        jdbcTemplate.update(sql, user_id, newProduct.getDate(), newProduct.getTime_of_day(),
                newProduct.getMealtime(), newProduct.getDescription(), newProduct.getQuantity());
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
