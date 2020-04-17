package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.model.newuser.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User getUser(String userName, String password);
}

