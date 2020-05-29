package nl.bioinf.fooddiary.dao.jdbc;

import ch.qos.logback.classic.pattern.Abbreviator;
import nl.bioinf.fooddiary.dao.VerifyProductRepository;
import nl.bioinf.fooddiary.model.newproduct.NewProduct;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class VerifyProductDAO implements VerifyProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NutrientNamesRowMapper rowMapper = new NutrientNamesRowMapper();

    public int getAllProductGroupNumbers() {
        String sql = "select count(distinct group_code) from product;";
        int amountOfGroupNumbers = jdbcTemplate.queryForObject(sql, Integer.class);
        return amountOfGroupNumbers;
    }

    public String getGroupCodeDescription(int groupcode) {
        String sql = "SELECT DISTINCT group_code_description FROM product WHERE group_code = ?";
        String groupCodeDescription = (String) jdbcTemplate.queryForObject(sql, String.class, groupcode);
        return groupCodeDescription;
    }

    @Override
    public List<NutrientNames> getNutrientNamesAndAbbr() {
        String sql = "SELECT distinct id, nutrient_code, name_english FROM nutrient";
        List<NutrientNames> nutrientNamesList = jdbcTemplate.query(sql, rowMapper);
        return nutrientNamesList;
    }


//    @Override
//    public List<String> getAllNutrientAbbreviations() {
//        List<String> nutrientValueAbbr = new ArrayList<>();
//        String sql = "SELECT DISTINCT nutrient_code FROM product_nutrient";
//        List<Map<String, Object>> rows =  jdbcTemplate.queryForList(sql);
//        for (int i = 0; i < rows.size(); i++) {
//            String nutrientAbbr = String.valueOf(rows.get(i));
//            nutrientAbbr = nutrientAbbr.substring(15, nutrientAbbr.length() - 1);
//            nutrientValueAbbr.add(nutrientAbbr);
//        }
//        return nutrientValueAbbr;
//    }
//
//    @Override
//    public List<String> getAllNutrientNames() {
//        List<String> nutrientValueNames = new ArrayList<>();
//        String sql = "SELECT DISTINCT name_english FROM nutrient";
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
//        for (int i = 0;  i < rows.size(); i++) {
//            String nutrientName = String.valueOf(rows.get(i));
//            nutrientName = nutrientName.substring(14, nutrientName.length() -1);
//            nutrientValueNames.add(nutrientName);
//        }
//        return nutrientValueNames;
//    }
}
