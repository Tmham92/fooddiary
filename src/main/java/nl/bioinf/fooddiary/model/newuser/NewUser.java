package nl.bioinf.fooddiary.model.newuser;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Hugo Donkerbroek
 *
 * Class that stores information about a new account from the new-user form
 */

public class NewUser {
    private int id;
    @NotNull
    @Size(min = 3)
    private String user_code;
    @NotNull
    @Size(min = 3)
    private String password;
    @NotNull
    private String role;
    private final int enabled = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "id=" + id +
                ", user_code='" + user_code + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
