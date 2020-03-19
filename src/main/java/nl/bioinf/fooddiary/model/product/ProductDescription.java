package nl.bioinf.fooddiary.model.product;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 *
 * See explanation about the use of the builder pattern in the javadoc from the ProductGroup.java.
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

    @Override
    public String toString() {
        return "ProductDescription{" +
                "descriptionDutch='" + descriptionDutch + '\'' +
                ", descriptionEnglish='" + descriptionEnglish + '\'' +
                ", synonymous='" + synonymous + '\'' +
                '}';
    }

    public static class ProductDescriptionBuilder {
        // Required parameters
        private final String descriptionDutch;
        private final String descriptionEnglish;

        // Optional parameter
        private String synonymous;

        private ProductDescriptionBuilder(String descriptionDutch, String descriptionEnglish) {
            this.descriptionDutch = descriptionDutch;
            this.descriptionEnglish = descriptionEnglish;
        }

        public ProductDescriptionBuilder synonymous(String synonymous) {
            if (synonymous.equals("")) {
                this.synonymous = "_UNKNOWN_SYNONYMOUS_"; return this;
            }

            this.synonymous = synonymous; return this;
        }

        public ProductDescription build() {return new ProductDescription(this); }
    }
}
