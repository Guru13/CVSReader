package by.huryanchyk.dao;

import by.huryanchyk.db.ConnectionFactory;
import by.huryanchyk.entity.User;
import by.huryanchyk.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;


/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The class implements all the necessary methods
 * for manipulation with user's data in database.
 */
public class UserDAOJDBCImpl implements UserDAO {

    private static final String SQL_GET_USERS_WITH_LIMIT = "SELECT name, surname, login, email, phoneNumber FROM user LIMIT ?, ? ";
    private static final String SQL_GET_USERS = "SELECT name, surname, login, email, phoneNumber FROM user;";
    private static final String SQL_INSERT = "INSERT INTO user (name, surname, login, email, phoneNumber) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE user SET name = ?, surname = ?, email = ?, phoneNumber = ? WHERE login = ?;";

    private Connection connection;

    public UserDAOJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> currentUsersList(int offset, int noOfRecords, String compareMethod) {

        List<User> listUsers = new ArrayList<User>();
        User user;
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_USERS_WITH_LIMIT);) {

            statement.setInt(1, offset);
            statement.setInt(2, noOfRecords);

            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {
                    user = new User();
                    user.setName(rs.getString("name"));
                    user.setSurName(rs.getString("surname"));
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phoneNumber"));

                    listUsers.add(user);
                }
            } catch (SQLException e) {
                throw new DaoException("Could not exexucute result set and get users", e);
            }
        } catch (SQLException e) {
            throw new DaoException("Could not get statement  and get users", e);
        }

        sortBy(compareMethod, listUsers);

        return listUsers;
    }

    @Override
    public List<User> getAllUsers() {

        String query = SQL_GET_USERS;
        List<User> listUsers = new ArrayList<User>();
        User user;

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setSurName(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Could not exexucute result set or get statement and get all users", e);
        }

        return listUsers;
    }

    @Override
    public void addAllUsers(List<User> users) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT);) {
            for (User user : users) {
                if (userExistLogin(user)) {
                    userUpdate(user);
                } else {
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getSurName());
                    statement.setString(3, user.getLogin());
                    statement.setString(4, user.getEmail());
                    statement.setString(5, user.getPhoneNumber());
                    statement.addBatch();
                }
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DaoException("Could not exexucute result set or get statement and add users", e);
        }
    }

    @Override
    public boolean userExistLogin(User user) {
        boolean isExist = false;
        List<User> users = getAllUsers();
        for (User currentUser : users) {
            if (currentUser.getLogin().equals(user.getLogin())) {
                isExist = true;
                break;
            }
        }
        return isExist;

    }

    @Override
    public boolean userExist(User user) {

        List<User> users = getAllUsers();

        return users.contains(user);
    }

    public long getNoOfRecords() {
        return getAllUsers().size();
    }

    /**
     * Updates data in database
     *
     * @param user an instance of {@code User} class that
     *             has to be update into table.
     */
    private void userUpdate(User user) {

        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Could not exexucute result set and update user", e);
        }
    }

    /**
     * Sorts data
     *
     * @param compareMethod method for sorting
     * @param listUsers     list for sorting
     */
    private void sortBy(String compareMethod, List<User> listUsers) {
        Comparator<User> comparator = null;

        if (compareMethod.equals("name")) {
            comparator = new NameComparator();
        } else if (compareMethod.equals("surname")) {
            comparator = new SurameComparator();
        } else if (compareMethod.equals("login")) {
            comparator = new LoginComparator();
        } else if (compareMethod.equals("email")) {
            comparator = new EmailComparator();
        } else if (compareMethod.equals("phoneNumber")) {
            comparator = new PhoneNumberComparator();
        }

        Collections.sort(listUsers, comparator);
    }

    /**
     * Inner class for compare data by name in database
     */
    private class NameComparator implements Comparator<User> {
        public int compare(User o1, User o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    /**
     * Inner class for compare data by surname in database
     */
    private class SurameComparator implements Comparator<User> {
        public int compare(User o1, User o2) {
            return o1.getSurName().compareTo(o2.getSurName());
        }
    }

    /**
     * Inner class for compare data by login in database
     */
    private class LoginComparator implements Comparator<User> {
        public int compare(User o1, User o2) {
            return o1.getLogin().compareTo(o2.getLogin());
        }
    }

    /**
     * Inner class for compare data by e-mail in database
     */
    private class EmailComparator implements Comparator<User> {
        public int compare(User o1, User o2) {
            return o1.getEmail().compareTo(o2.getEmail());
        }
    }

    /**
     * Inner class for compare data by phone number in database
     */
    private class PhoneNumberComparator implements Comparator<User> {
        public int compare(User o1, User o2) {
            return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
        }
    }
}
