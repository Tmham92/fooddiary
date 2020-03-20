package nl.bioinf.fooddiary.model.nutrient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 20-03-2020
 *
 * Class that is used to store all the information from the nutrient table from Nevo_online_2019 file into objects. For
 * more information about the builder pattern and the constraint validation see the ProductGroup class javadoc.
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

    /**
     * Inner builder class that serves the Nutrient class. This inner class uses constraint validation for the instance
     * variables.  Whenever a new product is added to the database the product needs to validated, the
     * constraints carry out those checks.
     */
    public static class NutrientBuilder {
        @NotNull
        @Size(min = 1, max = 25)
        private final String nutrientCode;

        @NotNull
        @Size(min = 1, max = 50)
        private final String nameDutch;

        @NotNull
        @Size(min = 1, max = 50)
        private final String nameEnglish;

        @NotNull
        @Size(min = 1, max = 10)
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
