package nl.bioinf.fooddiary.error_service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Tobias Ham
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
