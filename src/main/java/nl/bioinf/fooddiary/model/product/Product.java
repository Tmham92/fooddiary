package nl.bioinf.fooddiary.model.product;

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


    public Product(int code, ProductGroup productGroup) {
        this.code = code;
        this.productGroup = productGroup;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", productGroup=" + productGroup +
                '}';
    }
}
