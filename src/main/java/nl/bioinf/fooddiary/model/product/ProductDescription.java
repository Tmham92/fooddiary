package nl.bioinf.fooddiary.model.product;

import nl.bioinf.fooddiary.model.DataInputChecker;

/**
 * @author Tom Wagenaar
 * @version 0.0.5
 * date: 18-03-2020
 *
 * Class that represents the description in Dutch and in English and synonymous for each individual product. See the
 * ProductGroup class javadoc for some more information about the builder pattern that is used in this class. The
 * synonymous variable is optional and therefore isn't final. All the other variables are final and have no setters
 * to ensure immutability.
 */
public class ProductDescription {
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
     * string descriptionDutch and string descriptionEnglish as arguments, furthemore checking those values.
     * @param descriptionDutch (String)
     * @param descriptionEnglish (String)
     * @return ProductDescriptionBuilder object
     */
    public static ProductDescriptionBuilder builder(String descriptionDutch, String descriptionEnglish) {

        // Check the products dutch description, in between trim it.
        DataInputChecker.checkStringInputNull(descriptionDutch, "descriptionDutch");
        descriptionDutch = descriptionDutch.trim();
        DataInputChecker.checkInputLength(descriptionDutch, 255, "descriptionDutch");

        // Check the products description english, in between trim it.
        DataInputChecker.checkStringInputNull(descriptionEnglish, "descriptionEnglish");
        descriptionEnglish = descriptionEnglish.trim();
        DataInputChecker.checkInputLength(descriptionEnglish, 255, "descriptionEnglish");

        return new ProductDescriptionBuilder(descriptionDutch, descriptionEnglish);
    }

    // Getters
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
        return "{" +
                "descriptionDutch='" + descriptionDutch + '\'' +
                ", descriptionEnglish='" + descriptionEnglish + '\'' +
                ", synonymous='" + synonymous + '\'' +
                '}';
    }

    /**
     * Inner class that is used as a builder for the ProductDescription class. The synonymous variable isn't final and
     * therefore not required, whenever this synonymous variable isn't defined it gets the _UNKNOWN_SYNONYMOUS_ tag.
     */
    public static class ProductDescriptionBuilder {
        // Required parameters
        private final String descriptionDutch;
        private final String descriptionEnglish;

        // Optional parameter
        private String synonymous  = "_UNKNOWN_SYNONYMOUS_";

        private ProductDescriptionBuilder(String descriptionDutch, String descriptionEnglish) {
            this.descriptionDutch = descriptionDutch;
            this.descriptionEnglish = descriptionEnglish;
        }

        /**
         * Method that sets the synonymous. While parsing the nevo_online_2019_product.csv file there are fields with
         * "" as value, this method assign the _UNKNOWN_SYNONYMOUS_ value to it. Whenever a form is filled in and the
         * inner builder class is called and there isn't a value assigned to synonymous using .synonymous() a
         * _UNKNOWN_SYNONYMOUS_ value is also assigned, furthermore checks on null input and length.
         * @param synonymous
         * @return
         */
        public ProductDescriptionBuilder synonymous(String synonymous) {
            // Checks on null input.
            DataInputChecker.checkStringInputNull(synonymous, "synonymous");

            // Trim the synonymous.
            synonymous = synonymous.trim();

            // Assign value to synonymous.
            if (synonymous.equals("")) {
                this.synonymous = "_UNKNOWN_SYNONYMOUS_"; return this;
            } else {
                this.synonymous = synonymous;
            }

            // Check if length of synonymous is appropriate.
            DataInputChecker.checkInputLength(this.synonymous, 255, "synonymous");

            return this;
        }

        /**
         * Serves the ProductDescription class.
         * @return ProductDescription object with the parameters correspond to the instance variables.
         */
        public ProductDescription build() {return new ProductDescription(this); }
    }
}
