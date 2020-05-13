package nl.bioinf.fooddiary.dao.jdbc;


import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
public class ProductDAO implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private RowMapper<Product> rowMapper = new ProductRowMapper();

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
                return ProductMeasurement.builder(rs.getString("measurement_unit"), rs.getString("measurement_quantity")).measurementComment(rs.getString("measurement_comment")).build();
            }
        });
    }


    // TODO: !Reminder! Denk even na over of de product_id in recipe niet veranderd moet worden naar product_code - Tom
    /**
     * @author Tom Wagenaar
     * Date: 13-05-2020
     *
     * Method that receives a productDescription either in Dutch or English and retrieves the corresponding Product
     * from the product table in the fooddiary database.
     * @param productDescription, string that is either the description in Dutch or English.
     * @return Product that correspond to the description.
     */
    @Override
    public Product getSpecificProduct(String productDescription) {
        String sqlQuery = "select * from product where description_dutch = ?";
        return jdbcTemplate.queryForObject(sqlQuery, rowMapper, productDescription);
    }


}
