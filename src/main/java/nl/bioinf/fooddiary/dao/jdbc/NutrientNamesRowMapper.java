package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NutrientNamesRowMapper implements RowMapper<NutrientNames> {

    @Override
    public NutrientNames mapRow(ResultSet row, int rowNum) throws SQLException {
        NutrientNames nutrientNames = new NutrientNames();
        nutrientNames.setId(row.getInt("id"));
        nutrientNames.setName(row.getString("name_english"));
        nutrientNames.setAbbreviation(row.getString("nutrient_code"));
        nutrientNames.setMeasurementUnit(row.getString("measurement_unit"));
        return nutrientNames;
    }
}
