package nl.bioinf.fooddiary.model.nutrient;

import java.util.List;

public class NutrientNames {

    private int id;
    private String name;
    private String abbreviation;
    private String values;
    private String measurementUnit;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public NutrientNames() {
    }

}
