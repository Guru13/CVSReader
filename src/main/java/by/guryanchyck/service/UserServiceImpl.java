package by.guryanchyck.service;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.dao.UserDAOImpl;
import by.guryanchyck.entity.User;
import java.util.List;

/**
 * Created by Alexei Guryanchyck
 */
public class UserServiceImpl implements UserService{

    private UserDAO userDAO = new UserDAOImpl();


    public void addUser(User user) {
        userDAO.add(user);
    }

    public String getSortedMethod(String sortedMethod){
        if (sortedMethod == null){
            sortedMethod = "name";
        }
        return sortedMethod;
    }


    public List<User> values(int offset, int noOfRecords, String compareMethod) {
        return userDAO.values(offset, noOfRecords, compareMethod);
    }

    @Override
    public void addAllUsers(List<User> users) {
        userDAO.addAllUsers(users);
    }

    @Override
    public long getNoOfRecords() {
        return userDAO.getNoOfRecords();
    }
}
