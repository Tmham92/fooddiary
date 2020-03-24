package nl.bioinf.fooddiary.model.product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 * Class that represents the measurement -Unit, -Quanity and -Comment for each individual product. See the
 * ProductGroup class javadoc for some more information about the builder pattern and the constraints validation that
 * is used in this class and the inner builder class. The measurementComment variable is optional and therefore isn't
 * final, furthermore this variable has a setter. All the other variables are final and have no setters to ensure
 * immutability.
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
     * string measurementUnit and int measurementQuantity as arguments
     * @param measurementUnit (String)
     * @param measurementQuantity (int)
     * @return ProductDescriptionBuilder object
     */
    public static ProductMeasurementBuilder builder(String measurementUnit, int measurementQuantity) {
        return new ProductMeasurementBuilder(measurementUnit, measurementQuantity);
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
     * Inner class that is used as a builder for the ProductMeasurement class. There are constraints validations for the
     * instance variables. Whenever a new product is added to the database the product needs to validated, the
     * constraints carry out those checks. The measurementComment variable isn't final and therefore not required, whenever
     * this synonymous variable isn't defined it gets the _UNKNOWN_COMMENT_ tag.
     */
    public static class ProductMeasurementBuilder {
        // Required instance variable
        @NotNull
        @Size(min = 1, max = 10)
        private final String measurementUnit;
        @NotNull
        @Min(0) @Max(9999)
        private final int measurementQuantity;

        // Optional instance variable
        private String measurementComment = "_UNKNOWN_COMMENT_";

        private ProductMeasurementBuilder(String measurementUnit, int measurementQuantity) {
            this.measurementUnit = measurementUnit;
            this.measurementQuantity = measurementQuantity;
        }

        public ProductMeasurementBuilder measurementComment(String measurementComment) {
            // While parsing the nevo product table the measurementComment variables that aren't defined are "",
            // therefore they aren't seen as values that aren't defined.
            if (measurementComment.equals("")) {
                this.measurementComment = "_UNKNOWN_COMMENT_"; return this;
            }
            this.measurementComment = measurementComment; return this;
        }

        /**
         * Serves the ProductMeasurement class.
         * @return ProductMeasurement object that has the instance variables.
         */
        public ProductMeasurement build() { return new ProductMeasurement(this); }
    }
}
