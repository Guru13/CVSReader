package by.huryanchyk.service;

import by.huryanchyk.dao.UserDAO;
import by.huryanchyk.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The class has methods
 * for manipulation with user's data.
 */
public class UserServiceImpl implements UserService {

    private final int columnCount = 5;
    private UserDAO userDAO;
    private int recordsPerPage = 10;
    private String sortedMethod = "name";

    @Override
    public List<User> currentUsersList(int offset, int noOfRecords, String compareMethod) {
        return userDAO.currentUsersList(offset, noOfRecords, compareMethod);
    }

    @Override
    public void addAllUsers(String[] dataArray) {

        List<User> users = new ArrayList<>();

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
            users.add(user);
        }
        userDAO.addAllUsers(users);
    }

    @Override
    public long getNoOfRecords() {
        return userDAO.getNoOfRecords();
    }
    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    @Override
    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    @Override
    public String getSortedMethod() {
        return sortedMethod;
    }

    @Override
    public void setSortedMethod(String sortedMethod) {
        this.sortedMethod = sortedMethod;
    }
}
