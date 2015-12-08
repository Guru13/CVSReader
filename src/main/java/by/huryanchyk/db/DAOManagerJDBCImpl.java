package by.huryanchyk.db;

import by.huryanchyk.dao.UserDAO;
import by.huryanchyk.dao.UserDAOJDBCImpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 */
public class DAOManagerJDBCImpl implements DAOManager {

    private Connection connection;
    private UserDAO userDAO;

    public DAOManagerJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOJDBCImpl(getConnection());
        }
        return userDAO;
    }

    @Override
    public void beginTransaction()  {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (getConnection() != null) {
            getConnection().close();
        }
    }
}
