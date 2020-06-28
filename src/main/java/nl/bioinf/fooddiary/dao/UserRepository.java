package nl.bioinf.fooddiary.dao;

/**
 * @author Tom Wagenaar
 *
 * Interface that supports the UserDAO class, this interface get's the current user from the dataabase.
 */
public interface UserRepository {

    int getCurrentUser();
}
