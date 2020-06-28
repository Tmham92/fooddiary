package nl.bioinf.fooddiary.service;


import java.util.List;
/**
 @Author Tobias Ham
 */
public interface INutrientValuesDTO {

    List<String> getValues();

    void setValues(List<String> values);
}
