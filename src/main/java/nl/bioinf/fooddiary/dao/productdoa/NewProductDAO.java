package nl.bioinf.fooddiary.dao.productdoa;

import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class NewProductDAO implements IProductDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<NewProduct> getAllNewProducts() {
        String sql = "select id, description from unverified_product_entry";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public NewProduct getNewProduct(Integer id, String description) {
        String sql = "select id, discription from unverified_product_entry";
        RowMapper<NewProduct> rowMapper = new NewProductRowMapper();
        NewProduct newProduct = jdbcTemplate.queryForObject(sql, rowMapper, id, description);
        return newProduct;
    }
}
