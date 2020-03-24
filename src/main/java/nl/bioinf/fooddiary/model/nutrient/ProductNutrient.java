package nl.bioinf.fooddiary.model.nutrient;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 23-03-2020
 *
 * Class that stores the information about the code for a single product the nutrient values for that product and the
 * column names for those nutrient values. The information from this object is later on used to inject this data into
 * the database table Product_Nutrient, therefore these instance variable correspond to the fields in the
 * Product_Nutrient table. There are no setters and the instance variables are final, that ensures the immutability
 * of this class.
 */
public class ProductNutrient {
    private final int productCode;
    private final String nutrientCode;
    private final String nutrientValue;

    public ProductNutrient(int productCode, String nutrientCode, String nutrientValue) {
        this.productCode = productCode;
        this.nutrientCode = nutrientCode;
        this.nutrientValue = nutrientValue;
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
}
