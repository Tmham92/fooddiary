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
import java.util.Map;

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


    private  JdbcTemplate jdbcTemplate;


    private RowMapper<Product> rowMapper = new ProductRowMapper();

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getProductId(String lang,String description) {
        String sqlQuery = "select id from product where description_english = ?";
        if (lang.equals("nl")) {
            sqlQuery = "select id from product where description_dutch = ?";
        }
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
    public int insertProductIntoDiary(String lang,int userId, int productId, ProductEntry productEntry) {
        String mealtime = productEntry.getMealtime();
        if (lang.equals("nl")) {
            System.out.println(Mealtimes.getMealtimes());
            for (Map.Entry<String, String> entry : Mealtimes.getMealtimes().entrySet()) {
                if (entry.getValue().equals(productEntry.getMealtime())) {
                    mealtime = entry.getKey();
                }
            }
        }
            String sqlQuery = "insert into product_entry(user_id, product_id, date, time_of_day, mealtime, description) VALUES " +
                    "(?,?,?,?,?,?)";
            return jdbcTemplate.update(sqlQuery, userId, productId, productEntry.getDate(), productEntry.getTime(), mealtime,
                    productEntry.getDescription());
    }

    @Override
    public List<ProductEntry> getDiaryEntriesByDate(String lang, int idUser, String currentDate) {
        String sqlQuery = "select pe.id, user_id, product_id, description_dutch, description_english, measurement_quantity, measurement_unit, date, time_of_day, mealtime, description " +
                "from product_entry pe join product p on pe.product_id = p.code where pe.user_id = ? and pe.date = ?";
        return jdbcTemplate.query(sqlQuery, new Object[] { idUser, currentDate }, new RowMapper<ProductEntry>() {
            @Override
            public ProductEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
                String description = "";
                String mealtime = "";
                if (lang.equals("nl")) {
                    description = rs.getString("description_dutch");
                    mealtime = Mealtimes.getMealtimes().get(rs.getString("mealtime"));
                } else {
                    description = rs.getString("description_english");
                    mealtime = rs.getString("mealtime");
                }
                return new ProductEntry(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"),
                        description, rs.getDouble("measurement_quantity"), rs.getString("measurement_unit"),
                        rs.getString("date"), rs.getString("time_of_day"), mealtime, rs.getString("description"));
            }
        });
    }

    @Override
    public int removeDiaryEntryById(int diaryEntryId) {
        String sqlQuery = "delete from product_entry where id = ?";
        return jdbcTemplate.update(sqlQuery, diaryEntryId);
    }

    @Override
    public List<ProductOccurrence> getProductOccurrences(String lang) {
        String sqlQuery = "select product_id, description_dutch, count(product_id) as occurence from product_entry pe " +
                "join product p on pe.product_id = p.code  group by pe.product_id order by occurence DESC limit 10";
        return jdbcTemplate.query(sqlQuery, new RowMapper<ProductOccurrence>() {
            @Override
            public ProductOccurrence mapRow(ResultSet rs, int rowNum) throws SQLException {
                String productDescription = "";
                if(lang.equals("nl")) {
                    productDescription = rs.getString("description_dutch");
                } else {
                    productDescription = rs.getString("description_english");
                }
                return new ProductOccurrence(rs.getInt("product_id"), productDescription, rs.getInt("occurence"));
            }
        });
    }


    /**
     * @author Hans Zijlstra
     * Method that fetches all the product descriptions of the products in the database in  dutch
     * @return List<ProductDescription></> list of productdescriptions
     */

    @Override
    public List<String> getAllDutchProductDescriptions() {
        String sqlQuery = "select description_dutch from product;";
        return jdbcTemplate.queryForList(sqlQuery,String.class);
    }

    /**
     * @author Hans Zijlstra
     * Method that fetches all the product descriptions of the products in the database in english
     * @return List<ProductDescription></> list of productdescriptions
     */

    @Override
    public List<String> getAllEnglishProductDescriptions() {
        String sqlQuery = "select description_english from product;";
        return jdbcTemplate.queryForList(sqlQuery,String.class);
    }

    /**
     * @author Hans Zijlstra
     * Method that fetches the measuring unit for the selected product in the database
     * @param productId String description of food product
     * @return List<ProductDescription></> list of productdescriptions
     */

    @Override
    public String getMeasurementUnitByDescription(int productId) {
        String sqlQuery = "select measurement_unit from product where id = ?";
        return  jdbcTemplate.queryForObject(
                sqlQuery, new Object[] { productId }, String.class);
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
