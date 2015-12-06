package by.huryanchyk.dao;

import by.huryanchyk.entity.User;
import java.util.List;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The interface has all the necessary methods
 * for manipulation with user's data in database.
 */
public interface UserDAO {

    /**
     * Gets limited list of users from database .
     *
     * @param offset        number of initial record
     * @param noOfRecords   the number of returned records
     * @param compareMethod method for sorting
     * @return limited list of users.
     */
    List<User> currentUsersList(int offset, int noOfRecords, String compareMethod);

    /**
     * Gets the list of users from database.
     *
     * @return the list of users.
     */
    List<User> getAllUsers();

    /**
     * Adds all list of users to database.
     *
     * @param users is list that has to be placed into table.
     */
    void addAllUsers(List<User> users);

    /**
     * Check if user with login already exist in database
     *
     * @param user an instance of {@code User} for check
     * @return {@code true}if user login exist
     */
    boolean userExistLogin(User user);

    /**
     * Check if user  already exist in database
     *
     * @param user an instance of {@code User} for check
     * @return {@code true}if user login exist
     */
    boolean userExist(User user);

    /**
     * @return number of records in database
     */
    long getNoOfRecords();

}
