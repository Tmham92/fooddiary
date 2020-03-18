package nl.bioinf.fooddiary.model.product;

import javafx.util.Builder;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 *
 */
public class ProductGroup {
    // Instance variable declaration
    private final int groupCode;
    private final String groupCodeDescription;

    private ProductGroup(ProductGroupBuilder builder) {
        this.groupCode = builder.groupCode;
        this.groupCodeDescription = builder.groupCodeDescription;
    }


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

    public static class ProductGroupBuilder {
        private final int groupCode;
        private final String groupCodeDescription;

        private ProductGroupBuilder(int groupCode, String groupCodeDescription) {
            this.groupCode = groupCode;
            this.groupCodeDescription = groupCodeDescription;
        }

        public ProductGroup build() {
            return new ProductGroup(this);
        }
    }

}
