package nl.bioinf.fooddiary.model.product;

import nl.bioinf.fooddiary.model.nutrient.NutrientValues;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 * Class that stores information about a single product from the Nevo_Online_2019_Product.csv file. It stores a integer
 * code and several objects. These objects come from several classes in the model.product package.
 */
public class Product {


    // Instance variable declaration
    private int code;
    private ProductGroup productGroup;
    private ProductDescription productDescription;
    private ProductMeasurement productMeasurement;
    private ProductInfoExtra productInfoExtra;
    private NutrientValues nutrientValues;

    /**
     * Constructor receiving several object that contain information about each column in the Nevo_Online_2019_Product.
     * csv
     * @param code (int)
     * @param productGroup (ProductGroup object)
     * @param productDescription (ProductDescription object)
     * @param productMeasurement (ProductMeasurement object)
     * @param productInfoExtra (ProductInfoExtra object)
     * @param nutrientValues (NutrientValues object)
     */
    public Product(int code, ProductGroup productGroup, ProductDescription productDescription,
                   ProductMeasurement productMeasurement, ProductInfoExtra productInfoExtra, NutrientValues nutrientValues) {
        this.code = code;
        this.productGroup = productGroup;
        this.productDescription = productDescription;
        this.productMeasurement = productMeasurement;
        this.productInfoExtra = productInfoExtra;
        this.nutrientValues = nutrientValues;
    }

    public Product() {
    }

    // Setters

    public void setCode(int code) {
        this.code = code;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductMeasurement(ProductMeasurement productMeasurement) {
        this.productMeasurement = productMeasurement;
    }

    public void setProductInfoExtra(ProductInfoExtra productInfoExtra) {
        this.productInfoExtra = productInfoExtra;
    }

    public void setNutrientValues(NutrientValues nutrientValues) {
        this.nutrientValues = nutrientValues;
    }

    // Getters
    public int getCode() {
        return code;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public ProductMeasurement getProductMeasurement() {
        return productMeasurement;
    }

    public ProductInfoExtra getProductInfoExtra() {
        return productInfoExtra;
    }

    public NutrientValues getNutrientValues() {
        return nutrientValues;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", productGroup=" + productGroup +
                ", productDescription=" + productDescription +
                ", productMeasurement=" + productMeasurement +
                ", productInfoExtra=" + productInfoExtra +
                ", nutrientValues=" + nutrientValues +
                '}';
    }
}
