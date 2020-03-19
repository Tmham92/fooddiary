package nl.bioinf.fooddiary.model.product;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 * Class that represents the description in Dutch and in English and synonymous for each individual product. See the
 * ProductGroup class javadoc for some more information about the builder pattern and the constraints validation that
 * is used in this class and the inner builder class. The synonymous variable is optional and therefore isn't final,
 * furthermore this variable has a setter. All the other variables are final and have no setters to ensure immutability.
 *
 */
public final class ProductDescription {
    // Instance variable declaration
    private final String descriptionDutch;
    private final String descriptionEnglish;
    private String synonymous;

    private ProductDescription(ProductDescriptionBuilder builder) {
        this.descriptionDutch = builder.descriptionDutch;
        this.descriptionEnglish = builder.descriptionEnglish;
        this.synonymous = builder.synonymous;
    }

    /**
     * Static method that serves an instance of the inner class ProductDescriptionBuilder, taking the required
     * string descriptionDutch and string descriptionEnglish as arguments
     * @param descriptionDutch (String)
     * @param descriptionEnglish (String)
     * @return ProductDescriptionBuilder object
     */
    public static ProductDescriptionBuilder builder(String descriptionDutch, String descriptionEnglish) {
        return new ProductDescriptionBuilder(descriptionDutch, descriptionEnglish);
    }


    public String getDescriptionDutch() {
        return descriptionDutch;
    }

    public String getDescriptionEnglish() {
        return descriptionEnglish;
    }

    public String getSynonymous() {
        return synonymous;
    }

    /**
     * Synonymous isn't a important variable, therefore it isn't final. That is why there is a setter.
     * @param synonymous (String)
     */
    public void setSynonymous(String synonymous) {
        this.synonymous = synonymous;
    }

    @Override
    public String toString() {
        return "ProductDescription{" +
                "descriptionDutch='" + descriptionDutch + '\'' +
                ", descriptionEnglish='" + descriptionEnglish + '\'' +
                ", synonymous='" + synonymous + '\'' +
                '}';
    }

    /**
     * Inner class that is used as a builder for the ProductDescription class. There are constraints validations for the
     * instance variables. Whenever a new product is added to the database the product needs to validated, the
     * constraints carry out those checks. The synonymous variable isn't final and therefore not required, whenever
     * this synonymous variable isn't defined it gets the _UNKNOWN_SYNONYMOUS_ tag.
     */
    public static class ProductDescriptionBuilder {
        // Required parameters
        private final String descriptionDutch;
        private final String descriptionEnglish;

        // Optional parameter
        private String synonymous = "_UNKNOWN_SYNONYMOUS_";

        private ProductDescriptionBuilder(String descriptionDutch, String descriptionEnglish) {
            this.descriptionDutch = descriptionDutch;
            this.descriptionEnglish = descriptionEnglish;
        }

        public ProductDescriptionBuilder synonymous(String synonymous) {
            // While parsing the nevo product table the synonymous variables that aren't defined are "", therefore
            // they aren't seen as values that aren't defined.
            if (synonymous.equals("")) {
                this.synonymous = "_UNKNOWN_SYNONYMOUS_"; return this;
            }

            this.synonymous = synonymous; return this;
        }

        public ProductDescription build() {return new ProductDescription(this); }
    }
}
