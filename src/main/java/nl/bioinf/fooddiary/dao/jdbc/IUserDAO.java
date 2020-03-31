package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.model.User;

import java.util.List;

public interface IUserDAO {
    List<User> getAllUsers();

    User getUser(String userName, String password);
}
