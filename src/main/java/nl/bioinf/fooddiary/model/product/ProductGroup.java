package nl.bioinf.fooddiary.model.product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Tom Wagenaar
 * @version 0.0.3
 * date: 18-03-2020
 *
 * Class that represent the group code and group code description for each individual product in the
 * nevo_online_2019_Parser.csv file. This class makes uses of the so called builder pattern. An inner builder class
 * has been made that is implemented in such a way to give the class atomic construction, easy combination of parameters
 * and very readable client code, furthermore there are no setters and the class instance variables are final to
 * ensure immutability.
 *
 * In the inner builder class, constraints validation is used to give feedback to the spring framework whenever a
 * variable isn't valid.
 */
public final class ProductGroup {
    // Instance variable declaration
    private final int groupCode;
    private final String groupCodeDescription;

    /**
     * Constructor that takes a ProductGroupBuilder object (from the inner class) as an argument.
     * @param builder, ProductGroupBuilder object.
     */
    private ProductGroup(ProductGroupBuilder builder) {
        this.groupCode = builder.groupCode;
        this.groupCodeDescription = builder.groupCodeDescription;
    }


    /**
     * Static method that serves an instance of the inner ProductGroupBuilder class, taking the required groupCode
     * String and groupCodeDescription string as arguments. Also checks the groupCode and groupCodeDescription
     * on null input.
     * @param groupCode (String)
     * @param groupCodeDescription (String)
     * @return ProductGroupBuilder object
     */
    public static ProductGroupBuilder builder(String groupCode, String groupCodeDescription) {
        // TODO: Make a method that checks the groupCodeDescription on null input.
        int checkedGroupCode = checkGroupCode(groupCode);

        return new ProductGroupBuilder(checkedGroupCode, groupCodeDescription);
    }


    /**
     * Check the groupCode on null input.
     * @param groupCode (String)
     * @return checkedGroupCode
     */
    public static int checkGroupCode(String groupCode) {
        int checkedGroupCode = -1;

        // Change the groupCode String to an integer.
        try {
            checkedGroupCode = Integer.parseInt(groupCode);
        } catch (NumberFormatException numbException) {
            throw new NumberFormatException("Please don't provide a null value for group code!");
        }

        return checkedGroupCode;
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
     * Inner class that is used as a builder for the ProductGroup class. There are constraints validations for the
     * instance variables. Whenever a new product is added to the database the product needs to validated, the
     * constraints carry out those checks.
     */
    public static class ProductGroupBuilder {
        // Required parameters
        @NotNull
        @Min(1) @Max(999)
        private final int groupCode;

        @NotNull
        @Size(min = 1, max = 255)
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