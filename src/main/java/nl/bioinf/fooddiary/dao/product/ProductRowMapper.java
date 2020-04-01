package nl.bioinf.fooddiary.dao.product;

import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet row, int rowNum) throws SQLException {
        return new Product(row.getInt("code"),
                (ProductGroup) row.getObject("productGroup"),
                (ProductDescription) row.getObject("productDescription"),
                (ProductMeasurement) row.getObject("productMeasurement"),
                (ProductInfoExtra) row.getObject("productInfoExtra"),
                (NutrientValues) row.getObject("nutrientValues"));
    }
}
