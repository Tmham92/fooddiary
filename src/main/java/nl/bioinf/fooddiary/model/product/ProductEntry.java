package nl.bioinf.fooddiary.model.product;

import java.sql.Date;

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
    private String unit;
    private Date date;
    private String time;
    private String mealtime;
    private String description;

    public ProductEntry(String productDescription, String quantity, String unit, Date date, String time, String mealtime,String description) {
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

    public String getQuantity() {
        return quantity;
    }

    public Date getDate() {
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


}

