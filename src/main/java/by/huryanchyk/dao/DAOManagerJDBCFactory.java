package by.huryanchyk.dao;

import by.huryanchyk.db.ConnectionFactory;
import by.huryanchyk.exceptions.DaoException;

import java.sql.SQLException;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 */
public class DAOManagerJDBCFactory implements DAOManagerFactory {

    public DAOManager getDAOManager() {
        try {
            return new DAOManagerJDBCImpl(ConnectionFactory.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException("Could not connection to db and get users", e);
        }
    }

}
