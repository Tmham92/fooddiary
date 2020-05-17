package nl.bioinf.fooddiary.model.product;

/**
 * @author Hans Zijlstra
 */
public class ProductOccurrence {
    private int product_id;
    private String productDescription;
    private int occurence;

    public int getProduct_id() {
        return product_id;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getOccurence() {
        return occurence;
    }

    public ProductOccurrence(int product_id, String productDescription, int occurence) {
        this.product_id = product_id;
        this.productDescription = productDescription;
        this.occurence = occurence;
    }
}
