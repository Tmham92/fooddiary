package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import nl.bioinf.fooddiary.model.product.*;
import nl.bioinf.fooddiary.model.report.ReportValueWrapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Hans Zijlstra
 * RowMapper that is used for mapping rows of JbdcTemplate ResultSet
 * in which each row is mapped to a result object several product information are mapped
 * returns ReportValueWrapper
 */
public class DiaryReportMapper implements RowMapper<ReportValueWrapper> {
    @Override
    public ReportValueWrapper mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductEntry productEntry = ProductEntry.builder()
                .user_id(rs.getInt("user_id"))
                .quantity(rs.getDouble("quantity")).product_id(rs.getInt("product_id"))
                .date(rs.getString("date")).time(rs.getString("time_of_day"))
                .mealtime(rs.getString("mealtime")).description(rs.getString("description")).build();

        ProductDescription productDescription = ProductDescription.builder(
                rs.getString("description_english"),
                rs.getString("description_dutch"))
                .synonymous(rs.getString("synonymous"))
                .build();

        ProductGroup productGroup = ProductGroup.builder(rs.getString("group_code"), rs.getString("group_code_description")).build();
        ProductInfoExtra productInfoExtra = ProductInfoExtra.builder().enrichedWith(rs.getString("enriched_with")).tracesOf("traces_of").build();
        ProductMeasurement productMeasurement = ProductMeasurement.builder(rs.getString("measurement_unit"), rs.getString("measurement_quantity")).measurementComment(rs.getString("measurement_comment")).build();
        Nutrient nutrient = Nutrient.builder(rs.getString("nutrient_code"),rs.getString("description_english"), rs.getString("description_dutch"), rs.getString("measurement_unit")).build();
        double nutrientValue = rs.getDouble("nutrient_value");
        return new ReportValueWrapper(productEntry, productGroup, productDescription, productMeasurement, productInfoExtra, nutrientValue);
    }
}
