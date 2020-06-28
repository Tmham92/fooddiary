package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.newuser.NewUser;

public interface NewUserRepository {
    void addNewUser(NewUser newUser);
}
