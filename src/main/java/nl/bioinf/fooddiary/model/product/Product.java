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


    public Product(int code, ProductGroup productGroup, ProductDescription productDescription) {
        this.code = code;
        this.productGroup = productGroup;
        this.productDescription = productDescription;
    }


    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", productGroup=" + productGroup +
                ", productDescription=" + productDescription +
                '}';
    }
}
