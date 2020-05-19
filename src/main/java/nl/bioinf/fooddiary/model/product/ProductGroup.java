package nl.bioinf.fooddiary.model.product;

import nl.bioinf.fooddiary.model.DataInputChecker;

/**
 * @author Tom Wagenaar
 * @version 0.0.5
 * date: 18-03-2020
 *
 * Class that represent the group code and group code description for each individual product in the
 * nevo_online_2019_Parser.csv file. This class makes uses of the so called builder pattern. An inner builder class
 * has been made that is implemented in such a way to give the class atomic construction, easy combination of parameters
 * and very readable client code, furthermore there are no setters and the class instance variables are final to
 * ensure immutability.
 */
public final class ProductGroup {
    // Instance variable declaration
    private final int groupCode;
    private final String groupCodeDescription;

    /**
     * Constructor that takes a ProductGroupBuilder object (from the inner class) as an argument.
     * @param builder, ProductGroupBuilder object.
     */
    public ProductGroup(ProductGroupBuilder builder) {
        this.groupCode = builder.groupCode;
        this.groupCodeDescription = builder.groupCodeDescription;
    }




    /**
     * Static method that serves an instance of the inner ProductGroupBuilder class, taking the required groupCode
     * String and groupCodeDescription string as arguments. The parameters are passed to specific methods in the
     * ProductInputChecker class.
     * @param groupCode (String)
     * @param groupCodeDescription (String)
     * @return ProductGroupBuilder object
     */
    public static ProductGroupBuilder builder(String groupCode, String groupCodeDescription) {
        // Check the group code description, in between trim it.
        DataInputChecker.checkStringInputNull(groupCodeDescription, "groupCodeDescription");
        groupCodeDescription = groupCodeDescription.trim();
        DataInputChecker.checkInputLength(groupCodeDescription, 255, "groupCodeDescription");

        // Check the group code on null input and on the value.
        DataInputChecker.checkStringInputNull(groupCode, "groupCode");
        int checkedGroupCode = DataInputChecker.changeStringToInt(groupCode, "groupCode");
        DataInputChecker.checkInputSize(checkedGroupCode, 999, "groupCode");

        return new ProductGroupBuilder(checkedGroupCode, groupCodeDescription);
    }


    // Getter for the group code.
    public int getGroupCode() {
        return groupCode;
    }

    // Getter for the group code description
    public String getGroupCodeDescription() {
        return groupCodeDescription;
    }

    @Override
    public String toString() {
        return "{" +
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
         * @return ProductGroup object with the required parameters that correspond to the instance variables.
         */
        public ProductGroup build() {
            return new ProductGroup(this);
        }
    }
}