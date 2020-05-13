package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Tom Wagenaar
 * Date: 13-05-2020
 *
 *
 */
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        int productCode = rs.getInt("code");

        ProductGroup productGroup = ProductGroup.builder(rs.getString("group_code"),
                rs.getString("group_code_description")).build();

        ProductDescription productDescription1 = ProductDescription.builder(rs.getString("description_dutch"), rs.getString("description_english"))
                .synonymous(rs.getString("synonymous"))
                .build();


        ProductMeasurement productMeasurement = ProductMeasurement.builder(rs.getString("measurement_unit"), rs.getString("measurement_quantity"))
                .measurementComment(rs.getString("measurement_comment"))
                .build();

        ProductInfoExtra productInfoExtra = ProductInfoExtra.builder()
                .enrichedWith(rs.getString("enriched_with"))
                .tracesOf(rs.getString("traces_of"))
                .build();

        NutrientValues emptyNutrientValues = NutrientValues.builder().build();

        Product product = new Product(productCode, productGroup, productDescription1, productMeasurement,
                productInfoExtra, emptyNutrientValues);


        return product;
    }
}
