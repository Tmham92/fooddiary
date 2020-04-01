package nl.bioinf.fooddiary.dao.nutrient;

import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NutrientRowMapper implements RowMapper<Nutrient> {
    @Override
    public Nutrient mapRow(ResultSet row, int rowNum) throws SQLException {
        return Nutrient.builder(row.getString("nutrient_code"), row.getString("name_dutch"),
                row.getString("measurement_unit"), row.getString("name_english")).build();
    }
}
