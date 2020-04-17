package nl.bioinf.fooddiary.dao.newuserdao;

import nl.bioinf.fooddiary.model.newuser.NewUser;
import nl.bioinf.fooddiary.service.INewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hugo Donkerbroek
 *
 * This class inserts a new user in the database using an SQL query. The user data comes from the NewUser class.
 */

@Transactional
@Repository
public class NewUserDAO implements INewUserService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewUserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNewUser(NewUser newUser) {
        String sql = "INSERT INTO user " +
                "(id, user_code, password, role, enabled) values (?,?,?,?,?);";
        jdbcTemplate.update(sql, newUser.getId(), newUser.getUser_code(), newUser.getPassword(),
                newUser.getRole(), newUser.getEnabled());
    }
}
