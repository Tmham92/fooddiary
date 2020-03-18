package nl.bioinf.fooddiary.dao.userdao;

import nl.bioinf.fooddiary.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User getUser(String userName, String password);
}

