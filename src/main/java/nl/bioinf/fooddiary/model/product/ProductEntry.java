package nl.bioinf.fooddiary.model.product;

import java.util.Date;

/**
 * @author Hans Zijlstra
 * Class that respresents a product entry into the user his diary.
 * All the variables  have no setters to ensure immutability.
 * The parameters hold the name of the product and data that tells when the product
 * has been consumed and in which quantity
 */

public class ProductEntry {
    private int id;
    private int user_id;
    private int product_id;
    private String productDescription;
    private double quantity;
    private String unit;
    private String date;
    private String time;
    private String mealtime;
    private String description;

    public ProductEntry(int id, int user_id, int product_id, String productDescription, double quantity, String unit, String date, String time, String mealtime, String description) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.unit = unit;
        this.date = date;
        this.time = time;
        this.mealtime = mealtime;
        this.description = description;
    }

    public ProductEntry(String productDescription, double quantity, String unit, String date, String time, String mealtime, String description) {
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.unit = unit;
        this.date = date;
        this.time = time;
        this.mealtime = mealtime;
        this.description = description;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getQuantity() {
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

    public String getUnit() {
        return unit;
    }

    public int getId() { return id; }

    public int getUser_id() {
        return user_id;
    }

    public int getProduct_id() {
        return product_id;
    }
}

