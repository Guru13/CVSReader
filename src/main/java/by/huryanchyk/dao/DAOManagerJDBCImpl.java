package by.huryanchyk.dao;

import by.huryanchyk.exceptions.DaoException;

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
    public void beginTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Can't commit transaction", e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            throw new DaoException("Can't commit transaction", e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DaoException("Can't commit transaction", e);
        }
    }

    private Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (getConnection() != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                throw new DaoException("Can't commit transaction", e);
            }
        }
    }
}
