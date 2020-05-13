package nl.bioinf.fooddiary.model.nutrient;

import nl.bioinf.fooddiary.model.DataInputChecker;

/**
 * @author Tom Wagenaar
 * @version 0.0.2
 * date: 20-03-2020
 *
 * Class that is used to store all the information from the nutrient table from Nevo_online_2019 file into objects. For
 * more information about the builder pattern see the ProductGroup class javadoc.
 */
public class Nutrient {
    // Instance variable declaration
    private final String nutrientCode;
    private final String nameDutch;
    private final String nameEnglish;
    private final String measurementUnit;

    /**
     * Constructor that takes a NutrientBuilder object (from the inner class) as an argument.
     * @param builder (NutrientBuilder object)
     */
    public Nutrient(NutrientBuilder builder) {
        this.nutrientCode = builder.nutrientCode;
        this.nameDutch = builder.nameDutch;
        this.nameEnglish = builder.nameEnglish;
        this.measurementUnit = builder.measurementUnit;
    }

    /**
     * Static method that serves an instance of the inner NutrientBuilder class, taking the required nutrientCode,
     * nameDutch, nameEnglish and measureUnit as argument.
     * @param nutrientCode (int)
     * @param nameDutch (String)
     * @param nameEnglish (String)
     * @param measurementUnit (String)
     * @return NutrientBuilder object.
     */
    public static NutrientBuilder builder(String nutrientCode, String nameDutch,
                                          String nameEnglish, String measurementUnit) {

        // Check the nutrientCode on null input and length.
        DataInputChecker.checkStringInputNull(nutrientCode, "nutrientCode");
        nutrientCode = nutrientCode.trim();
        DataInputChecker.checkInputLength(nutrientCode, 25, "nutrientCode");

        // Check the nameDutch on null input and length.
        DataInputChecker.checkStringInputNull(nameDutch, "nameDutch");
        nameDutch = nameDutch.trim();
        DataInputChecker.checkInputLength(nameDutch, 255, "nameDutch");

        // Check the nameEnglish on null input and length.
        DataInputChecker.checkStringInputNull(nameEnglish, "nameEnglish");
        nameEnglish = nameEnglish.trim();
        DataInputChecker.checkInputLength(nameEnglish, 255, "nameEnglish");

        // Check the measurementUnit on null input and length.
        DataInputChecker.checkStringInputNull(measurementUnit, "measurementUnit");
        measurementUnit = measurementUnit.trim();
        DataInputChecker.checkInputLength(measurementUnit, 255, "measurementUnit");

        return new NutrientBuilder(nutrientCode, nameDutch, nameEnglish, measurementUnit);
    }

    // GETTERS
    public String getNutrientCode() {
        return nutrientCode;
    }

    public String getNameDutch() {
        return nameDutch;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    @Override
    public String toString() {
        return "Nutrient = {" +
                "nutrientCode=" + nutrientCode +
                ", nameDutch='" + nameDutch + '\'' +
                ", nameEnglish='" + nameEnglish + '\'' +
                ", measurementUnit='" + measurementUnit + '\'' +
                '}';
    }

    // Inner builder class that serves the Nutrient class with the required variables.
    public static class NutrientBuilder {
        // Required variables.
        private final String nutrientCode;
        private final String nameDutch;
        private final String nameEnglish;
        private final String measurementUnit;

        public NutrientBuilder(String nutrientCode, String nameDutch, String nameEnglish, String measurementUnit) {
            this.nutrientCode = nutrientCode;
            this.nameDutch = nameDutch;
            this.nameEnglish = nameEnglish;
            this.measurementUnit = measurementUnit;
        }

        /**
         * Serves the Nutrient class with instance variables.
         * @return Nutrient object.
         */
        public Nutrient build() {return new Nutrient(this); }
    }
}
