package nl.bioinf.fooddiary.dao.product;


import nl.bioinf.fooddiary.model.csvparser.ProductCsvParser;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * This class represent the Data Access Object or DAO for the product table in the fooddairy database. The DAO allows
 * for isolation of the application layer from the database layer. Both layers can evolve separately without knowing
 * anything from each other. This class implements the ProductRepository that keeps the domain model completely
 * decoupled from the database layer. This class supports inserting data into the product table using sql query,
 * retrieving the product description for one signle product.
 */
@Repository
public class ProductDAO implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;

    private ProductRowMapper mapper;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @Author Tom Wagenaar
     * Method that when called insert a single product into the product table from the fooddiary database. This is
     * accomplished using a sql query and jdbcTemplate update method.
     * @param product, One single Product object
     * @return jdbcTemplate.update, containing a sql query and the used product objects.
     */
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
