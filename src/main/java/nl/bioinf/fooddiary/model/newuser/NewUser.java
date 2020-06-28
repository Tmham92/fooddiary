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
    private String userCode;
    @NotNull
    @Size(min = 3)
    private String password;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public void setRole(Role role) {
        this.role = role.toString();
    }

    public int getEnabled() {
        return 1;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "id=" + id +
                ", user_code='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
