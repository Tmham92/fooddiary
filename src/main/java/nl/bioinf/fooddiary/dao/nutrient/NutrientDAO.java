package nl.bioinf.fooddiary.dao.nutrient;

import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NutrientDAO implements NutrientRepository {

    private final JdbcTemplate jdbcTemplate;
    private NutrientRowMapper mapper;

    @Autowired
    public NutrientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertNutrientData(Nutrient nutrient) {
        String sqlQuery = "INSERT INTO nutrient (nutrient_code, name_dutch, name_english, measurement_unit) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, nutrient.getNutrientCode(), nutrient.getNameDutch(),
                nutrient.getNameEnglish(), nutrient.getMeasurementUnit());
    }
}
