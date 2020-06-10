package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.DiaryReportDAO;
import nl.bioinf.fooddiary.model.newuser.NewUser;
import nl.bioinf.fooddiary.model.newuser.NewUserReport;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.report.ReportEntry;
import nl.bioinf.fooddiary.model.report.ReportValueWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author Hans Zijlstra
 */
@Repository
public class ReportDAO implements DiaryReportDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> getProjects() {
        String sqlQuery = "select name from project";
        return jdbcTemplate.query(sqlQuery, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("name");
            }
        });
    }



    @Override
    public List<NewUserReport> getUserFromProject(String projectName) {
        String sqlQuery = "select u.id, user_code from user u join user_project up on u.id = up.user_id join project p on up.project_id = p.id where p.name = ? and u.role = 'ROLE_USER'";
        return jdbcTemplate.query(sqlQuery, new Object[]{projectName}, new RowMapper<NewUserReport>() {
            @Override
            public NewUserReport mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new NewUserReport(rs.getInt("id"), rs.getString("user_code"));
            }
        });
    }

    @Override
    public List<ReportValueWrapper> getUserReport(ReportEntry reportEntry) {
        String sqlQuery = String.format("select user_id, product_id, quantity, date, time_of_day, mealtime, description, group_code, group_code_description, description_dutch, " +
                "description_english, synonymous, p.measurement_unit, measurement_quantity, measurement_comment, enriched_with, traces_of, n.nutrient_code, name_dutch, name_english, n.measurement_unit, prod_nun.nutrient_value " +
                "from product_entry join product p on product_entry.product_id = p.code join product_nutrient prod_nun on p.code = prod_nun.product_code " +
                "join nutrient n on n.nutrient_code = prod_nun.nutrient_code where date between ? and ? and user_id in (%s)", reportEntry.getUsers().toArray());
            return jdbcTemplate.query(sqlQuery, new Object[] {reportEntry.getDateFrom(), reportEntry.getDateTil()}, new DiaryReportMapper());
    }

    @Override
    public List<NutrientValues.NutrientValue> getNutrientValues(int productId) {
        String sqlQuery = "select nutrient_value from product_nutrient where product_code = ? ";
        return jdbcTemplate.queryForList(sqlQuery, NutrientValues.NutrientValue.class, productId);
    }


}
