package nl.bioinf.fooddiary.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
/*
@author Tobias Ham
 */
public class NewProduct {


    private Integer id;
    private Integer user_id;
    @NotNull
    private Date date;
    @NotNull
    private String time_of_day;
    @NotNull
    private String mealtime;
    @NotNull
    private String description;
    @Min(0)
    @NotNull
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
                quantity + " User_id: " + user_id + " Date: " + date + " Time of Day: " +
                time_of_day + " Mealtime: " + mealtime;
        return message;
    }

}
