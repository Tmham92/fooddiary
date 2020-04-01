package nl.bioinf.fooddiary.dao.productnutrient;

import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductNutrientDAO implements ProductNutrientRepository {

    private final JdbcTemplate jdbcTemplate;

    private ProductNutrientMapper mapper;

    @Autowired
    public ProductNutrientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertProductNutrientData(ProductNutrient productNutrient) {
        String sqlQuery = "INSERT INTO product_nutrient (product_code, nutrient_code, nutrient_value) VALUES (?, ?, ?)";

        return jdbcTemplate.update(sqlQuery, productNutrient.getProductCode(),
                productNutrient.getNutrientCode(), productNutrient.getNutrientValue());
    }
}