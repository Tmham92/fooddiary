package nl.bioinf.fooddiary.dao.productnutrient;

import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductNutrientMapper implements RowMapper<ProductNutrient> {
    @Override
    public ProductNutrient mapRow(ResultSet row, int rowNum) throws SQLException {
        return ProductNutrient.builder(row.getInt("product_code"), row.getString("nutrient_code"), row.getString("nutrient_value"))
                .build();
    }


}
