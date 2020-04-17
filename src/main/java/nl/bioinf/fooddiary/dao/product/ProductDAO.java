package nl.bioinf.fooddiary.dao.product;


import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import nl.bioinf.fooddiary.model.product.ProductMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDAO implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertProductData(Product product) {
        String sqlQuery = "INSERT INTO product (code, group_code, group_code_description, description_dutch, " +
                "description_english, synonymous, measurement_unit, measurement_quantity, measurement_comment, " +
                "enriched_with, traces_of) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sqlQuery, product.getCode(),
                product.getProductGroup().getGroupCode(), product.getProductGroup().getGroupCodeDescription(),
                product.getProductDescription().getDescriptionDutch(), product.getProductDescription().getDescriptionEnglish(),
                product.getProductDescription().getSynonymous(), product.getProductMeasurement().getMeasurementUnit(),
                product.getProductMeasurement().getMeasurementQuantity(), product.getProductMeasurement().getMeasurementComment(),
                product.getProductInfoExtra().getEnrichedWith(), product.getProductInfoExtra().getTracesOf());
    }


    /**
     * @author Hans Zijlstra
     * Method that fetches all the product descriptions of the products in the database in english and dutch
     * @return List<ProductDescription></> list of productdescriptions
     */

    @Override
    public List<ProductDescription> getAllProductDescriptions() {
        String sqlQuery = "select description_dutch, description_english, synonymous from product;";
        return jdbcTemplate.query(sqlQuery, new RowMapper<ProductDescription>() {
            @Override
            public ProductDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ProductDescription.builder(rs.getString("description_dutch"), rs.getString("description_english")).synonymous(rs.getString("synonymous")).build();
            }
        });
    }

    /**
     * @author Hans Zijlstra
     * Method that fetches the measuring unit for the selected product in the database
     * @return List<ProductDescription></> list of productdescriptions
     */

    @Override
    public ProductMeasurement getProductByDescription(String description) {
        String sqlQuery = "select measurement_quantity  from product where description_dutch = ? or where description_english = ?";
        return (ProductMeasurement) jdbcTemplate.query(sqlQuery, new RowMapper<ProductMeasurement>() {
            @Override
            public ProductMeasurement mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ProductMeasurement.builder(rs.getString("measurement_unit"), rs.getInt("measurement_quantity")).measurementComment(rs.getString("measurement_comment")).build();
            }
        });
    }


}
