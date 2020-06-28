package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.NewUserRepository;
import nl.bioinf.fooddiary.model.newuser.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Hugo Donkerbroek
 *
 * This class inserts a new user in the database using an SQL query. The user data comes from the NewUser class.
 */

@Repository
public class NewUserDAO implements NewUserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewUserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * SQL query to insert the user data into the database.
     * @param newUser the user data; user_code, password, role
     */
    @Override
    public void addNewUser(NewUser newUser) {
        String sql = "INSERT INTO user " +
                "(id, user_code, password, role, enabled) values (?,?,?,?,?);";
        jdbcTemplate.update(sql, newUser.getId(), newUser.getUserCode(), newUser.getPassword(),
                newUser.getRole(), newUser.getEnabled());
    }
}
