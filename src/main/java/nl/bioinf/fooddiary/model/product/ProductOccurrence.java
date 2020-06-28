package nl.bioinf.fooddiary.model.product;

/**
 * @author Hans Zijlstra
 */
public class ProductOccurrence {
    private int product_id;
    private String productDescription;
    private int occurence;
    private String meassurementUnit;
    private String mealtime;


    public ProductOccurrence(int product_id, String productDescription, int occurence, String meassurementUnit, String mealtime) {
        this.product_id = product_id;
        this.productDescription = productDescription;
        this.occurence = occurence;
        this.meassurementUnit = meassurementUnit;
        this.mealtime = mealtime;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getOccurence() {
        return occurence;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getMeassurementUnit() {
        return meassurementUnit;
    }

    public String getMealtime() {
        return mealtime;
    }
}
