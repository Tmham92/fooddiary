package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.model.newproduct.NewProduct;
import nl.bioinf.fooddiary.model.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class UnverifiedProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addNewProduct(Product product, ProductGroup productGroup, ProductDescription productDescription,
                              ProductMeasurement productMeasurement, ProductInfoExtra productInfoExtra) {
        String sql = "INSERT INTO product " +
                "(code, group_code, group_code_description, description_dutch, description_english, synonymous, measurement_unit," +
                " measurement_quantity, measurement_comment, enriched_with, traces_of) values (?,?,?,?,?,?,?,?,?,?,?);";

        jdbcTemplate.update(sql, product.getCode(), productGroup.getGroupCode(), productGroup.getGroupCodeDescription(), productDescription.getDescriptionDutch(),
                productDescription.getDescriptionEnglish(), productDescription.getSynonymous(), productMeasurement.getMeasurementUnit()
                , productMeasurement.getMeasurementQuantity(), productMeasurement.getMeasurementComment(), productInfoExtra.getEnrichedWith()
                , productInfoExtra.getTracesOf());
    }

}
