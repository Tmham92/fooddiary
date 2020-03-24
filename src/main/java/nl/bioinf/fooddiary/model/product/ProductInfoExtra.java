package nl.bioinf.fooddiary.model.product;


/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 19-03-2020
 *
 * Class that represents enriched with and traces of columns for each individual product. See the
 * ProductGroup class javadoc for some more information about the builder pattern and the constraints validation that
 * is used in this class and the inner builder class. Both variables are optional and therefore they can be changed.
 */
public class ProductInfoExtra {
    private String enrichedWith;
    private String tracesOf;

    public ProductInfoExtra(ProductInfoExtraBuilder builder) {
        this.enrichedWith = builder.enrichedWith;
        this.tracesOf = builder.tracesOf;
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

    // Setters for the variables
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
     * Inner class that is used as a builder for the ProductInfoExtra class. There are constraints validations for the
     * instance variables. Whenever a new product is added to the database the product needs to validated, the
     * constraints carry out those checks. The measurementComment variable isn't final and therefore not required, whenever
     * this synonymous variable isn't defined it gets the _UNKNOWN_COMMENT_ tag.
     */
    public static class ProductInfoExtraBuilder {
        // Optional
        private String enrichedWith = "_UNKNOWN_INFO_";
        private String tracesOf = "_UNKNOWN_INFO_";

        /**
         * Constructor with no required parameters.
         */
        private ProductInfoExtraBuilder() { }

        public ProductInfoExtraBuilder enrichedWith(String enrichedWith) {
            // While parsing the nevo product table the enrichedWith variables that aren't defined are "",
            // therefore they aren't seen as values that aren't defined.
            if (enrichedWith.equals("")) {
                this.enrichedWith = "_UNKNOWN_INFO_"; return this;
            }
            this.enrichedWith = enrichedWith; return this;
        }

        public ProductInfoExtraBuilder tracesOf(String tracesOf) {
            // While parsing the nevo product table the tracesOf variables that aren't defined are "",
            // therefore they aren't seen as values that aren't defined.
            if (tracesOf.equals("")) {
                this.tracesOf = "_UNKNOWN_INFO_"; return this;
            }
            this.tracesOf = tracesOf; return this;
        }

        /**
         * Serves the ProductInfoExtra class.
         * @return ProductInfoExtra object that has the instance variables.
         */
        public ProductInfoExtra build() { return new ProductInfoExtra(this); }
    }
}
