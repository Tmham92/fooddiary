package nl.bioinf.fooddiary.model.newuser;

/**
 * @author Hans Zijlstra
 */
public class NewUserReport {
    private int id;
    private String user_code;

    public NewUserReport(int id, String user_code) {
        this.id = id;
        this.user_code = user_code;
    }

    public int getId() {
        return id;
    }

    public String getUser_code() {
        return user_code;
    }
}
