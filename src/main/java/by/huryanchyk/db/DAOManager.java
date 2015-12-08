package by.huryanchyk.db;

import by.huryanchyk.dao.UserDAO;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 */
public interface DAOManager extends AutoCloseable {

    UserDAO getUserDAO();

    /**
     * Opens transaction
     */
    void beginTransaction();

    /**
     * Commits(close) transaction
     */
    void commitTransaction();

    /**
     *Cancels transaction
     */
    void rollbackTransaction();
}
