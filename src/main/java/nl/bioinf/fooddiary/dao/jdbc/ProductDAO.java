package nl.bioinf.fooddiary.dao.jdbc;


import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private  JdbcTemplate jdbcTemplate;


    private RowMapper<Product> rowMapper = new ProductRowMapper();

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getProductId(String description) {
        String sqlQuery = "select id from product where description_dutch = ?";
        return jdbcTemplate.queryForObject(
                sqlQuery, new Object[] { description }, Integer.class);
    }

    @Override
    public int getUserIdByUsername(String username) {
        String sqlQuery = "select id from user where user_code = ? ";
        return jdbcTemplate.queryForObject(
                sqlQuery, new Object[] { username }, Integer.class);
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
     * Inserts information regarding consumed products into the diary database
     * @param userId int The users id
     * @param productId int id of consumed product
     * @param productEntry productEntry object containing product data
     * @return List<ProductDescription></> list of productdescriptions
     */


    @Override
    public int insertProductIntoDiary(int userId, int productId, ProductEntry productEntry) {
        String sqlQuery = "insert into product_entry(user_id, product_id, date, time_of_day, mealtime, description) VALUES " +
                "(?,?,?,?,?,?)";
        return jdbcTemplate.update(sqlQuery, userId, productId, productEntry.getDate(), productEntry.getTime(), productEntry.getMealtime(),
                productEntry.getDescription());
    }

    @Override
    public List<ProductEntry> getDiaryEntriesByDate(int idUser, String currentDate) {
        String sqlQuery = "select pe.id, user_id, product_id, description_dutch, measurement_quantity, measurement_unit, date, time_of_day, mealtime, description " +
                "from product_entry pe join product p on pe.product_id = p.code where pe.user_id = ? and pe.date = ?";
        return jdbcTemplate.query(sqlQuery, new Object[] { idUser, currentDate }, new RowMapper<ProductEntry>() {
            @Override
            public ProductEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ProductEntry(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"),
                        rs.getString("description_dutch"), rs.getDouble("measurement_quantity"), rs.getString("measurement_unit"),
                        rs.getString("date"), rs.getString("time_of_day"), rs.getString("mealtime"), rs.getString("description"));
            }
        });
    }

    @Override
    public int removeDiaryEntryById(int diaryEntryId) {
        String sqlQuery = "delete from product_entry where id = ?";
        return jdbcTemplate.update(sqlQuery, diaryEntryId);
    }


    /**
     * @author Hans Zijlstra
     * Method that fetches all the product descriptions of the products in the database in english and dutch
     * @return List<ProductDescription></> list of productdescriptions
     */

    @Override
    public List<ProductDescription> getAllProductDescriptions() {
        String sqlQuery = "select description_dutch, description_english from product;";
        return jdbcTemplate.query(sqlQuery, new RowMapper<ProductDescription>() {
            @Override
            public ProductDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ProductDescription.builder(rs.getString("description_dutch"), rs.getString("description_english")).build();
            }
        });
    }

    /**
     * @author Hans Zijlstra
     * Method that fetches the measuring unit for the selected product in the database
     * @param description String description of food product
     * @return List<ProductDescription></> list of productdescriptions
     */

    @Override
    public String getMeasurementUnitByDescription(String description) {
        String sqlQuery = "select measurement_unit from product where description_dutch = ?";
        return (String) jdbcTemplate.queryForObject(
                sqlQuery, new Object[] { description }, String.class);
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
