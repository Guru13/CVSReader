package by.guryanchyck.service;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.dao.UserDAOImpl;
import by.guryanchyck.entity.User;

import java.util.List;

/**
 * Created by Alexei Guryanchyck
 */
public class UserServiceImpl implements UserService {

    private final int columnCount = 5;
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

    public void addUser(String[] dataArray) {
        for (int i = 4; i < dataArray.length - 1; i++) {
            String[] columns = dataArray[i].split(";");

            if (columns.length != columnCount) {
                continue;
            }
            String name = columns[0];
            String surname = columns[1];
            String login = columns[2];
            String email = columns[3];
            String phoneNumber = columns[4];
            User user = new User(name, surname, login, email, phoneNumber);
            userDAO.add(user);
        }
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
