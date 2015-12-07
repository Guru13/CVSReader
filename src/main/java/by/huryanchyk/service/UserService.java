package by.huryanchyk.service;

import by.huryanchyk.entity.User;
import java.util.List;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The interface has methods
 * for manipulation with user's data.
 */
public interface UserService {

    /**
     * Gets current list of users
     *<p/>
     * starting counting users from @param offset
     * @param noOfRecords number of records on the page
     * @param compareMethod method for compare users
     * @return
     */
    List<User> currentUsersList(int offset, int noOfRecords, String compareMethod);

    /**
     * Convert text data in array to user and
     * adds users to list and adds list of users to database
     *
     * @param dataArray text data for conversion
     */
    void addAllUsers(String[] dataArray);

    /**
     * Gets number of all records in the database
     *
     * @return number of records in the database
     */
    long getNoOfRecords();


}
