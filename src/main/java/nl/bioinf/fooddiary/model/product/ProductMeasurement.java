package nl.bioinf.fooddiary.model.product;

import nl.bioinf.fooddiary.model.DataInputChecker;

/**
 * @author Tom Wagenaar
 * @version 0.0.2
 * date: 18-03-2020
 *
 * Class that represents the measurement -Unit, -Quanity and -Comment for each individual product. See the
 * ProductGroup class javadoc for some more information about the builder pattern that is used in this class and the
 * inner builder class. The measurementComment variable is optional and therefore isn't final. All the other variables
 * are final and have no setters to ensure immutability.
 */
public class ProductMeasurement {
    // Instance variable declaration
    private final String measurementUnit;
    private final int measurementQuantity;
    private String measurementComment;

    private ProductMeasurement(ProductMeasurementBuilder builder) {
        this.measurementUnit = builder.measurementUnit;
        this.measurementQuantity = builder.measurementQuantity;
        this.measurementComment = builder.measurementComment;
    }

    /**
     * Static method that serves an instance of the inner class ProductMeasurementBuilder, taking the required
     * string measurementUnit and string measurementQuantity as arguments. The measurementQuantity is transformed into
     * an integer, furthermore some checks are carried out on both the unit and the quantity.
     * @param measurementUnit (String)
     * @param measurementQuantity (String)
     * @return ProductDescriptionBuilder object
     */
    public static ProductMeasurementBuilder builder(String measurementUnit, String measurementQuantity) {
        // Check the Product measurement unit on null input and length, in between carry out a trim.
        DataInputChecker.checkStringInputNull(measurementUnit, "measurementUnit");
        measurementUnit = measurementUnit.trim();
        DataInputChecker.checkInputLength(measurementUnit, 10, "measurementUnit");

        // Check the measurementQuantity on null input, change it to an integer and if the integer is between a range.
        DataInputChecker.checkStringInputNull(measurementQuantity, "measurementQuantity");
        int checkedMeasurementQuantity = DataInputChecker.changeStringToInt(measurementQuantity, "measurementQuantity");
        DataInputChecker.checkInputSize(checkedMeasurementQuantity, 9999, "measurementQuantity");

        return new ProductMeasurementBuilder(measurementUnit, checkedMeasurementQuantity);
    }

    // Getters for the instance variables
    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public int getMeasurementQuantity() {
        return measurementQuantity;
    }

    public String getMeasurementComment() {
        return measurementComment;
    }

    @Override
    public String toString() {
        return "{" +
                "measurementUnit='" + measurementUnit + '\'' +
                ", measurementQuantity=" + measurementQuantity +
                ", measurementComment='" + measurementComment + '\'' +
                '}';
    }

    /**
     * Inner class that is used as a builder for the ProductMeasurement class. The measurementComment variable isn't
     * final and therefore not required, whenever this synonymous variable isn't defined it gets the _UNKNOWN_COMMENT_ tag.
     */
    public static class ProductMeasurementBuilder {
        // Required instance variable
        private final String measurementUnit;
        private final int measurementQuantity;

        // Optional instance variable
        private String measurementComment = "_UNKNOWN_COMMENT_";

        private ProductMeasurementBuilder(String measurementUnit, int measurementQuantity) {
            this.measurementUnit = measurementUnit;
            this.measurementQuantity = measurementQuantity;
        }

        public ProductMeasurementBuilder measurementComment(String measurementComment) {
            // Check if the measurementComment is a null value and trim it.
            DataInputChecker.checkStringInputNull(measurementComment, "measurementComment");
            measurementComment = measurementComment.trim();

            // Assign a value to the comment.
            if (measurementComment.equals("")) {
                this.measurementComment = "_UNKNOWN_COMMENT_"; return this;
            } else {
                this.measurementComment = measurementComment;
            }

            // Check the length of the comment.
            DataInputChecker.checkInputLength(this.measurementComment, 510, "measurmentComment");

            return this;
        }

        /**
         * Serves the ProductMeasurement class.
         * @return ProductMeasurement object that has the instance variables.
         */
        public ProductMeasurement build() { return new ProductMeasurement(this); }
    }
}
