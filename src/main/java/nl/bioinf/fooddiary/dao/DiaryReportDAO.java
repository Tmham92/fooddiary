package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.newuser.NewUser;
import nl.bioinf.fooddiary.model.newuser.NewUserReport;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.report.ReportEntry;
import nl.bioinf.fooddiary.model.report.ReportValueWrapper;

import java.util.List;

/**
 * @author Hans Zijlstra
 */
public interface DiaryReportDAO {

    List<String> getProjects();

    List<NewUserReport> getUserFromProject(String projectName);

    List<ReportValueWrapper> getUserReport(ReportEntry reportEntry);

    List<NutrientValues.NutrientValue> getNutrientValues(int productId);
}
