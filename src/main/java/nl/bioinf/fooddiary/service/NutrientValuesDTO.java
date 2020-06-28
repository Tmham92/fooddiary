package nl.bioinf.fooddiary.service;

import java.util.List;
/**
 @Author Tobias Ham
 */
public class NutrientValuesDTO implements INutrientValuesDTO {
    private List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
