package nl.bioinf.fooddiary.model.product;

import javafx.util.Builder;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 * Class that represent the group code and group code description for each individual product in the
 * nevo_online_2019_Parser.csv file. This class makes uses of the so called builder pattern. An inner builder class
 * has been made that is implemented in such a way to give the class atomic construction, easy combination of parameters
 * and very readable client code, furthermore there are no setters and the class and instance variables are final to
 * ensure immutability.
 */
public final class ProductGroup {
    // Instance variable declaration
    private final int groupCode;
    private final String groupCodeDescription;

    /**
     * Constructor that takes a ProductGroupBuilder object as an argument.
     * @param builder, ProductGroupBuilder object.
     */
    private ProductGroup(ProductGroupBuilder builder) {
        this.groupCode = builder.groupCode;
        this.groupCodeDescription = builder.groupCodeDescription;
    }


    /**
     * Static method that serves an instance of the inner ProductGroupBuilder class, taking the required groupcode
     * integer and groupdCodeDescription string as arguments.
     * @param groupCode (int)
     * @param groupCodeDescription (String)
     * @return ProductGroupBuilder object
     */
    public static ProductGroupBuilder builder(int groupCode, String groupCodeDescription) {
        return new ProductGroupBuilder(groupCode, groupCodeDescription);
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
                "groupCode=" + groupCode +
                ", groupCodeDescription='" + groupCodeDescription + '\'' +
                '}';
    }

    /**
     * Inner class that is used as a builder for the ProductGroup class.
     */
    public static class ProductGroupBuilder {
        // Required parameters
        private final int groupCode;
        private final String groupCodeDescription;

        private ProductGroupBuilder(int groupCode, String groupCodeDescription) {
            this.groupCode = groupCode;
            this.groupCodeDescription = groupCodeDescription;
        }

        /**
         * Serves the ProductGroup class.
         * @return ProductGroup object with the required parameters.
         */
        public ProductGroup build() {
            return new ProductGroup(this);
        }
    }
}