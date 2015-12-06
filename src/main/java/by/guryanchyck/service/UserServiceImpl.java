package by.guryanchyck.service;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.dao.UserDAOImpl;
import by.guryanchyck.entity.User;
import java.util.List;

/**
 * Created by Alexei Guryanchyck
 */
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;
    private int recordsPerPage = 10;
    private String sortedMethod = "name";


    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getSortedMethod() {
        return sortedMethod;
    }

    public void setSortedMethod(String sortedMethod) {
        this.sortedMethod = sortedMethod;
    }

    public void addUser(User user) {
        userDAO.add(user);
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


    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
