package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 * @author Tom Wagenaar
 *
 * Repository that is used to get data from the current user out of the daabase.
 */
@Repository
public class UserDAO implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get the current user that is logged in the application and check if it is authenticated. Then get the id of the
     * user and return it.
     * @return Id of the current user.
     */
    @Override
    public int getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object[] param = new Object[] {
                auth.getName()
        };
        String sqlGetId = "select id from user where user_code = ?";
        int user_id = jdbcTemplate.queryForObject(sqlGetId, param, Integer.class);
        return user_id;
    }
}
