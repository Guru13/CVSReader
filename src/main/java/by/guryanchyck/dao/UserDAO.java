package by.guryanchyck.dao;

import by.guryanchyck.db.ConnectionFactory;
import by.guryanchyck.entity.User;

import java.sql.*;
import java.util.*;

/**
 * Created by Alexey Guryanchyck on 30.08.2015.
 *
 * The class implements all the necessary methods
 * for manipulation with user's data in database.
 */
public class UserDAO {

    private Connection connection;
    private Statement stmt;
    private long noOfRecords;
    private static final String SQL_GET_USERS_WITH_LIMIT = "SELECT name, surname, login, email, phoneNumber FROM user limit ";
    private static final String SQL_GET_USERS = "SELECT name, surname, login, email, phoneNumber FROM user;";
    private static final String SQL_INSERT = "INSERT INTO user (name, surname, login, email, phoneNumber) VALUES (?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE user SET name = ?, surname = ?, email = ?, phoneNumber = ? WHERE login = ?;";

    public UserDAO() {
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = ConnectionFactory.getInstance().getConnection();
        return con;
    }

    /**
     * Gives the list of users from database.
     *
     * @param offset        number of initial record
     * @param noOfRecords   the number of returned records
     * @param compareMethod method for sorting
     * @return the list of users.
     */
    public List<User> values(int offset, int noOfRecords, String compareMethod) {

        String query = SQL_GET_USERS_WITH_LIMIT + offset + ", " + noOfRecords;
        List<User> listUsers = new ArrayList<User>();
        User user = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setSurName(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                listUsers.add(user);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        sortBy(compareMethod, listUsers);
        return listUsers;
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
     * Gives the list of users from database.
     *
     * @return the list of users.
     */
    public List<User> getAllUsers() {

        String query = SQL_GET_USERS;
        List<User> listUsers = new ArrayList<User>();
        User user = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setSurName(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                listUsers.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listUsers;
    }

    /**
     * Adds  user to database.
     *
     * @param user is an instance of {@code User} class that
     *             has to be placed into table.
     */
    public void add(User user) {
        if (userExistLogin(user)) {
            userUpdate(user);
        } else {
            userInsert(user);
        }
    }

    /**
     * insert user into database
     *
     * @param user an instance of {@code User} class that
     *             has to be placed into table.
     */
    private void userInsert(User user) {
        PreparedStatement statement;
        try {
            connection = getConnection();
            statement = this.connection.prepareStatement(SQL_INSERT);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates data in database
     *
     * @param user an instance of {@code User} class that
     *             has to be update into table.
     */
    private void userUpdate(User user) {
        PreparedStatement statement;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getLogin());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param user an instance of {@code User} for check
     * @return {@code true}if user login exist
     */
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

    /**
     * @param user an instance of {@code User} for check
     * @return {@code true}if user  exist
     */
    public boolean userExist(User user) {

        List<User> users = getAllUsers();

        return users.contains(user);

    }

    /**
     * Closes connection with database
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return number of records in database
     */
    public long getNoOfRecords() {
        return getAllUsers().size();
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
