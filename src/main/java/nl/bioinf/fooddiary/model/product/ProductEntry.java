package nl.bioinf.fooddiary.model.product;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "product description is mandatory")
    private String productDescription;
    @NotBlank(message = "Quantity is mandatory")
    private double quantity;
    @NotBlank(message = "Unit is mandatory")
    private String unit;

    @Override
    public String toString() {
        return "ProductEntry{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", productDescription='" + productDescription + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", mealtime='" + mealtime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public ProductEntry(){

    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
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

    @NotBlank(message = "Date is mandatory")
    private String date;
    @NotBlank(message = "Time is mandatory")
    private String time;
    @NotBlank(message = "mealtime is mandatory")
    private String mealtime;
    private String description;

    public ProductEntry(Builder builder) {
        this.id = builder.id;
        this.user_id = builder.user_id;
        this.product_id = builder.product_id;
        this.productDescription = builder.productDescription;
        this.quantity = builder.quantity;
        this.unit = builder.unit;
        this.date = builder.date;
        this.time = builder.time;
        this.mealtime = builder.mealtime;
        this.description = builder.description;
    }

    public static class Builder {
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


        public Builder id(int id){
            this.id = id;
            return this;

        }

        public Builder user_id(int user_id) {
            this.user_id = user_id;
            return this;
        }

        public Builder product_id(int product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Builder mealtime(String mealtime) {
            this.mealtime = mealtime;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public ProductEntry build() {
            return new ProductEntry(this);
        }




    }

    public static Builder builder() {
        return new Builder();
    }



}

