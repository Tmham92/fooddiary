package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.VerifyProductRepository;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.Product;
import org.assertj.core.internal.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VerifyProductDAO implements VerifyProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NutrientNamesRowMapper rowMapper = new NutrientNamesRowMapper();

    public String getGroupCodeDescription(int groupcode) {
        String sql = "SELECT DISTINCT group_code_description FROM product WHERE group_code = ?";
        String groupCodeDescription = (String) jdbcTemplate.queryForObject(sql, String.class, groupcode);
        return groupCodeDescription;
    }

    @Override
    public List<NutrientNames> getNutrientNamesAndAbbr() {
        String sql = "SELECT distinct id, nutrient_code, name_english, measurement_unit FROM nutrient";
        List<NutrientNames> nutrientNamesList = jdbcTemplate.query(sql, rowMapper);
        for (NutrientNames nutrientNames : nutrientNamesList) {
            nutrientNames.setValues("_NO_VALUE_");
        }
        return nutrientNamesList;
    }

    @Override
    public boolean checkProductCode(int code) {
        try {
            String sql = "SELECT id FROM product WHERE code = ?";
            jdbcTemplate.queryForObject(sql, Long.class, code);
            return false;
        } catch (EmptyResultDataAccessException e) {
            return true;
        }
    }

    @Override
    public void submitProductInfoToDatabase(Product product) {
        String sql = "INSERT INTO product (code, group_code, group_code_description, description_dutch, description_english, "
                + " synonymous, measurement_unit, measurement_quantity, measurement_comment, enriched_with, traces_of) values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getCode(), product.getProductGroup().getGroupCode(), product.getProductGroup().getGroupCodeDescription(),
                product.getProductDescription().getDescriptionDutch(), product.getProductDescription().getDescriptionEnglish(),
                product.getProductDescription().getSynonymous(), product.getProductMeasurement().getMeasurementUnit(),
                product.getProductMeasurement().getMeasurementQuantity(), product.getProductMeasurement().getMeasurementComment(),
                product.getProductInfoExtra().getEnrichedWith(), product.getProductInfoExtra().getTracesOf());
    }

    @Override
    public List<String> getNutrientCodes() {
        List<String> nutrientCodes;
        String sql = "select distinct nutrient_code from product_nutrient ORDER BY id ASC";
        nutrientCodes = jdbcTemplate.queryForList(sql, String.class);
        return nutrientCodes;
    }

    @Override
    public void submitProductNutrientsToDatabase(Product product) {
        String sql = "INSERT INTO product_nutrient (product_code, nutrient_code, nutrient_value) values (?, ?, ?)";
        int productCode = product.getCode();
        List<String> nutrientCodes = getNutrientCodes();

        int[] updateCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, productCode);
                ps.setString(2, nutrientCodes.get(i));
                ps.setString(3, product.getNutrientValues().getNutrients().get(i).getValue());
            }

            @Override
            public int getBatchSize() {
                return product.getNutrientValues().getNutrients().size();
            }
        });
    }

    @Override
    public int getHighestProductCode() {
        String sql = "SELECT MAX(code) FROM product";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void deleteVerifiedProductFromUnverified(Integer id) {
        String sql = "DELETE FROM unverified_product_entry WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public String getProductPicture(int productId) {
        String sql = "SELECT unverified_product_picture_location FROM unverified_product_picture WHERE " +
                "unverified_product_id = ?";
        String location = jdbcTemplate.queryForObject(sql, String.class, productId);
        System.out.println("____IN DOA GOING BACK TO SERVICE_____");
        System.out.println(location);
        return location;
    }
}
