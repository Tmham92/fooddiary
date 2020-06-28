package nl.bioinf.fooddiary.model.product;

/**
 * @author Hans Zijlstra
 * Class that keeps track how often a product occurres in a persons food diary
 * It stores information about the product like measurement unit and at what time the product was taken
 */
public class ProductOccurrence {
    private int productId;
    private String productDescription;
    private int occurence;
    private String meassurementUnit;
    private String mealtime;


    public ProductOccurrence(int product_id, String productDescription, int occurence, String meassurementUnit, String mealtime) {
        this.productId = product_id;
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

    public int getProductId() {
        return productId;
    }

    public String getMeassurementUnit() {
        return meassurementUnit;
    }

    public String getMealtime() {
        return mealtime;
    }
}
