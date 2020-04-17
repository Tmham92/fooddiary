package nl.bioinf.fooddiary.dao.nutrient;

import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 *
 * This class represent the Data Access Object or DAO for the nutrient table in the fooddairy database. The DAO allows
 * for isolation of the application layer from the database layer. Both layers can evolve separately without knowing
 * anything from each other. This class implements the NutrientRepository that keeps the domain model completely
 * decoupled from the database layer. This class supports inserting data into the nutrient table using sql query.
 */
@Repository
public class NutrientDAO implements NutrientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NutrientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Method that supports inserting nutrient data into the database, using sql query and jdbcTemplate update method.
     * @param nutrient Nutrient object, see Nutrient class under model/nutrient/Nutrient
     * @return jdbcTemplate.update, update the nutrient table with one single nutrient.
     */
    @Override
    public int insertNutrientData(Nutrient nutrient) {
        String sqlQuery = "INSERT INTO nutrient (nutrient_code, name_dutch, name_english, measurement_unit) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, nutrient.getNutrientCode(), nutrient.getNameDutch(),
                nutrient.getNameEnglish(), nutrient.getMeasurementUnit());
    }
}
