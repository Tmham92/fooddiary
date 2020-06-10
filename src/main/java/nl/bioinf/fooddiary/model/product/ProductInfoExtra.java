package nl.bioinf.fooddiary.model.product;


import nl.bioinf.fooddiary.model.DataInputChecker;

/**
 * @author Tom Wagenaar
 * @version 0.0.2
 * date: 19-03-2020
 *
 * Class that represents enriched with and traces of columns for each individual product. See the
 * ProductGroup class javadoc for some more information about the builder pattern  that is used in this class and the
 * inner builder class. Both variables are optional.
 */
public class ProductInfoExtra {
    private String enrichedWith;
    private String tracesOf;

    public ProductInfoExtra(ProductInfoExtraBuilder builder) {
        this.enrichedWith = builder.enrichedWith;
        this.tracesOf = builder.tracesOf;
    }

    public ProductInfoExtra() {
    }

    /**
     * Static method that serves an instance of the inner class ProductMeasurementBuilder, taking no required
     * parameters.
     * @return ProductDescriptionBuilder object
     */
    public static ProductInfoExtraBuilder builder() {
        return new ProductInfoExtraBuilder();
    }

    // Getters for the instance variables
    public String getEnrichedWith() {
        return enrichedWith;
    }

    public String getTracesOf() {
        return tracesOf;
    }

    // Setters

    public void setEnrichedWith(String enrichedWith) {
        this.enrichedWith = enrichedWith;
    }

    public void setTracesOf(String tracesOf) {
        this.tracesOf = tracesOf;
    }

    @Override
    public String toString() {
        return "{" +
                "enrichedWith='" + enrichedWith + '\'' +
                ", tracesOf='" + tracesOf + '\'' +
                '}';
    }

    /**
     * Inner class that is used as a builder for the ProductInfoExtra class. The measurementComment variable isn't final
     * and therefore not required, whenever this synonymous variable isn't defined it gets the _UNKNOWN_INFO_ value.
     */
    public static class ProductInfoExtraBuilder {
        // Optional
        private String enrichedWith = "_UNKNOWN_INFO_";
        private String tracesOf = "_UNKNOWN_INFO_";

        // Constructor without any parameters.
        private ProductInfoExtraBuilder() { }

        public ProductInfoExtraBuilder enrichedWith(String enrichedWith) {
            // Check if the enriched with value is a null value.
            DataInputChecker.checkStringInputNull(enrichedWith, "enrichedWith");
            enrichedWith = enrichedWith.trim();

            // assign a value to the enrichedWith variable.
            if (enrichedWith.equals("")) {
                this.enrichedWith = "_UNKNOWN_INFO_"; return this;
            } else {
                this.enrichedWith = enrichedWith;
            }

            // Check the length of the enrichedWith variable.
            DataInputChecker.checkInputLength(enrichedWith, 255, "enrichedWith");

            return this;
        }

        public ProductInfoExtraBuilder tracesOf(String tracesOf) {
            // Check if the traces of value is a null value.
            DataInputChecker.checkStringInputNull(tracesOf, "tracesOf");
            tracesOf = tracesOf.trim();

            // assign a value to the tracesOf variable.
            if (tracesOf.equals("")) {
                this.tracesOf = "_UNKNOWN_INFO_"; return this;
            } else {
                this.tracesOf = tracesOf;
            }

            // Check the length of the tracesOf variable.
            DataInputChecker.checkInputLength(tracesOf, 255, "tracesOf");
            return this;
        }

        /**
         * Serves the ProductInfoExtra class.
         * @return ProductInfoExtra object that has the instance variables.
         */
        public ProductInfoExtra build() { return new ProductInfoExtra(this); }
    }
}
