package by.guryanchyck.dao;

import by.guryanchyck.entity.User;

import java.util.List;

/**
 * Created by Alexei Guryanchyck
 */
public interface UserDAO {

   List<User> values(int offset, int noOfRecords, String compareMethod);
    List<User> getAllUsers();
    void add(User user);
    boolean userExistLogin(User user);
    boolean userExist(User user);
    void close();
    long getNoOfRecords();
}
