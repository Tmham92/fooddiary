package nl.bioinf.fooddiary.dao.productdoa;

import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewProductRowMapper implements RowMapper<NewProduct>{
    @Override
    public NewProduct mapRow(ResultSet row, int rowNum) throws SQLException {
        return new NewProduct();
    }

}
