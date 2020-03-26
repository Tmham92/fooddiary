package nl.bioinf.fooddiary.dao.productnutrient;

import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductNutrientDAO implements ProductNutrientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductNutrientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public ProductNutrient getProductNutrientById(int idPattern) {
        return null;
    }
}
