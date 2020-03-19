package nl.bioinf.fooddiary.model.product;

import javax.validation.constraints.Size;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 *
 */
public class Product {
    // Instance variable declaration
    private final int code;
    private final ProductGroup productGroup;
    private ProductDescription productDescription;
    private ProductMeasurement productMeasurement;
    private ProductInfoExtra productInfoExtra;


    public Product(int code, ProductGroup productGroup, ProductDescription productDescription,
                   ProductMeasurement productMeasurement, ProductInfoExtra productInfoExtra) {
        this.code = code;
        this.productGroup = productGroup;
        this.productDescription = productDescription;
        this.productMeasurement = productMeasurement;
        this.productInfoExtra = productInfoExtra;
    }

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

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", productGroup=" + productGroup +
                ", productDescription=" + productDescription +
                ", productMeasurement=" + productMeasurement +
                ", productInfoExtra=" + productInfoExtra +
                '}';
    }
}
