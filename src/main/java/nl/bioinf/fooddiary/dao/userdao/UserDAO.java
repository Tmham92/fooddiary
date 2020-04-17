package nl.bioinf.fooddiary.dao.userdao;

import nl.bioinf.fooddiary.dao.jdbc.IUserDAO;
import nl.bioinf.fooddiary.model.newuser.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        String sql = "select user_name, password from users";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public User getUser(String userName, String password) {
        String sql = "select user_name, password from users where user_name = ? and password = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        User user = jdbcTemplate.queryForObject(sql, rowMapper, userName, password);
        return user;
    }
}


