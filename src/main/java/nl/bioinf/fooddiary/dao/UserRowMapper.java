//package nl.bioinf.fooddiary.dao;
//
//import nl.bioinf.fooddiary.model.User;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserRowMapper implements RowMapper<User> {
//    @Override
//    public User mapRow(ResultSet row, int rowNum) throws SQLException {
//        return new User(row.getString("user_name"),row.getString("password"));
//
//    }
//
//
//}
