package by.guryanchyck.service;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexei Guryanchyck
 */
public interface UserService {

    void addUser(User user);

    String getSortedMethod(String sortedMethod);

    List<User> values(int offset, int noOfRecords, String compareMethod, UserDAO userDAO);
}
