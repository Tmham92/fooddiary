package nl.bioinf.fooddiary.error_service;

public class LoginError {
    private final String error;

    public LoginError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
