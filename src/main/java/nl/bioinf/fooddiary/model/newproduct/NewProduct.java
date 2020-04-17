package nl.bioinf.fooddiary.model.newproduct;

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
    private Integer user_id;
    @NotNull
    private String date;
    @NotNull
    private String time_of_day;
    @NotNull
    private String mealtime;
    @NotNull
    private String description;
    private String quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public String getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(String time_of_day) {
        this.time_of_day = time_of_day;
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
                time_of_day + " Mealtime: " + mealtime;
        return message;
    }

}
