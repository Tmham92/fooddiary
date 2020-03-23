package nl.bioinf.fooddiary.model;

import java.sql.Time;
import java.util.Date;

public class NewProduct {

    private Integer id;
    private Integer user_id;
    private Date date;
    private Time time_of_day;
    private String mealtime;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime_of_day() {
        return time_of_day;
    }

    public void setTime_of_day(Time time_of_day) {
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
}
