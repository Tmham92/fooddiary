package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.jdbc.IUserDAO;
import nl.bioinf.fooddiary.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUser(String userName, String password) {
        return userDAO.getUser(userName, password);
    }
}
