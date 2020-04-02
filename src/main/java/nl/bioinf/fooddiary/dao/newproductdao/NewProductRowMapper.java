package nl.bioinf.fooddiary.dao.newproductdao;


import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class NewProductRowMapper implements RowMapper<NewProduct>{
    @Override
    public NewProduct mapRow(ResultSet row, int rowNum) throws SQLException {
        NewProduct newProduct = new NewProduct();
        newProduct.setId(row.getInt("id"));
        newProduct.setUser_id(row.getInt("user_id"));
        newProduct.setDescription(row.getString("description"));
        newProduct.setDate(row.getString("date"));
        newProduct.setTime_of_day(row.getString("time_of_day"));
        newProduct.setMealtime(row.getString("mealtime"));
        newProduct.setQuantity(row.getString("quantity"));
        return newProduct;
    }
}
