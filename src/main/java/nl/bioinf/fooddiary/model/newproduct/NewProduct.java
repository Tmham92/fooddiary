package nl.bioinf.fooddiary.model.newproduct;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Tobias Ham
 * @version 0.0.1
 * date
 *
 * Class to store information about unknown product which users can submit using the new product form.
 */

public class NewProduct {

    private Integer id;
    private Integer userId;
    @NotEmpty(message = "{NotEmpty.newproductform.date}") @NotNull
    private String date;
    @NotEmpty(message = "{NotEmpty.newproductform.time_of_day}") @NotNull
    private String timeOfDay;
    @NotEmpty @NotNull
    private String mealtime;
    @NotEmpty(message = "{NotEmpty.newproductform.description}") @NotNull
    private String description;
    @NotEmpty(message = "{NotEmpty.newproductform.quantity}") @NotNull
    private String quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getMealtime() {
        return mealtime;
    }

    public void setMealtime(String mealtime) {
        this.mealtime = mealtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String toString() {
        String message = " Description: " + description + " Quantity: " +
                quantity + " Date: " + date + " Time of Day: " +
                timeOfDay + " Mealtime: " + mealtime;
        return message;
    }

}
