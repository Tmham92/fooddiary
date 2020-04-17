package nl.bioinf.fooddiary.dao.product;


import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
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

    private ProductRowMapper mapper;

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

    @Override
    public List<ProductDescription> getAllProductsByDescription() {
        String sqlQuery = "select description_dutch, description_english, synonymous from product;";
        return jdbcTemplate.query(sqlQuery, new RowMapper<ProductDescription>() {
            @Override
            public ProductDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ProductDescription.builder(rs.getString("description_dutch"), rs.getString("description_english")).synonymous(rs.getString("synonymous")).build();
            }
        });
    }
}
