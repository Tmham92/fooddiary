package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.jdbc.NewUserDAO;
import nl.bioinf.fooddiary.model.newuser.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hugo Donkerbroek
 */

@Service
public class NewUserService implements INewUserService {

    @Autowired
    private NewUserDAO newUserDAO;

    @Override
    public void addNewUser(NewUser newUser) {
        newUserDAO.addNewUser(newUser);
    }
}
