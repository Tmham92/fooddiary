package nl.bioinf.fooddiary.model.product;

/**
 * @author Hans Zijlstra
 * Class that respresents a product entry into the user his diary.
 * All the variables  have no setters to ensure immutability.
 * The parameters hold the name of the product and data that tells when the product
 * has been consumed and in which quantity
 */

public class ProductEntry {
    private String productDescription;
    private String quantity;
    private String date;
    private String time;
    private String mealtime;
    private String description;


    public String getProductDescription() {
        return productDescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMealtime() {
        return mealtime;
    }

    public String getDescription() {
        return description;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMealtime(String mealtime) {
        this.mealtime = mealtime;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

