package nl.bioinf.fooddiary.error_service;

/**
 * @author Hans Zijlstra
 * Class that displays a login error when authentication at login fails
 */

public class LoginError {
    private final String error;

    public LoginError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
