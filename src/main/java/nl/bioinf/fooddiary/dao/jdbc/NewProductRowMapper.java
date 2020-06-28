package nl.bioinf.fooddiary.dao.jdbc;


import nl.bioinf.fooddiary.model.newproduct.NewProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Tobias Ham
 * @version 0.0.1
 * date 17-03-2020
 *
 * Class that creates a newProduct object from values in the database using RowMapper.
 */

public class NewProductRowMapper implements RowMapper<NewProduct>{

    @Override
    public NewProduct mapRow(ResultSet row, int rowNum) throws SQLException {
        NewProduct newProduct = new NewProduct();
        newProduct.setId(row.getInt("id"));
        newProduct.setUserId(row.getInt("user_id"));
        newProduct.setDescription(row.getString("description"));
        newProduct.setDate(row.getString("date"));
        newProduct.setTimeOfDay(row.getString("time_of_day"));
        newProduct.setMealtime(row.getString("mealtime"));
        newProduct.setQuantity(row.getString("quantity"));
        return newProduct;
    }
}
