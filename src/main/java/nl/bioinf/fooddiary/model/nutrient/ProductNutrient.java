package nl.bioinf.fooddiary.model.nutrient;

import nl.bioinf.fooddiary.model.ProductNutrientInputChecker;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 23-03-2020
 *
 * Class that represents the linking table between the product and nutrient table in the fooddairy database. This
 * class has productCode variable that represent the a single product in the product table. The nutrientCode variable
 * represent a single nutrient in the nutrient table. A single product contains multiple nutrientCodes, therefore
 * multiple nutrientValues. In the class there aren't any setters and the instance variables are final to ensure
 * immutability. For more information about the builder pattern that is used see the ProductGroup javadoc.
 *
 * There is no need for checking the ProductCode and NutrientCode values, because these values are already checked in
 * the Product and Nutrient classes!
 */
public class ProductNutrient {
    private final int productCode;
    private final String nutrientCode;
    private final String nutrientValue;

    public ProductNutrient(ProductNutrientBuilder builder) {
        this.productCode = builder.productCode;
        this.nutrientCode = builder.nutrientCode;
        this.nutrientValue = builder.nutrientValue;
    }

    public static ProductNutrientBuilder builder(int productCode, String nutrientCode, String nutrientValue) {

        // Check the nutrientValue on null input and length.
        ProductNutrientInputChecker.checkStringInputNull(nutrientValue, "nutrientValue");
        nutrientValue = nutrientValue.trim();
        ProductNutrientInputChecker.checkInputLength(nutrientValue, 15, "nutrientValue");

        return new ProductNutrientBuilder(productCode, nutrientCode, nutrientValue);
    }

    // Getters
    public int getProductCode() {
        return productCode;
    }

    public String getNutrientCode() {
        return nutrientCode;
    }

    public String getNutrientValue() {
        return nutrientValue;
    }

    @Override
    public String toString() {
        return "{" +
                "productCode=" + productCode +
                ", nutrientCode='" + nutrientCode + '\'' +
                ", nutrientValue='" + nutrientValue + '\'' +
                '}';
    }

    // Inner builder class that serves the ProductNutrient class.
    public static class ProductNutrientBuilder {
        private final int productCode;
        private final String nutrientCode;
        private final String nutrientValue;

        public ProductNutrientBuilder(int productCode, String nutrientCode, String nutrientValue) {
            this.productCode = productCode;
            this.nutrientCode = nutrientCode;
            this.nutrientValue = nutrientValue;
        }

        public ProductNutrient build() {return new ProductNutrient(this); }
    }
}
