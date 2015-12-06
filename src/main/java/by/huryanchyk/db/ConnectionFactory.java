package by.huryanchyk.db;

import by.huryanchyk.service.Settings;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 *
 * The  class represents an ability to connect with database.
 */
public class ConnectionFactory {

    private final static Logger logger = Logger.getLogger(ConnectionFactory.class);

    private static ConnectionFactory instance = new ConnectionFactory();
    private final Settings settings;

    private ConnectionFactory() {
        settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
        } catch (ClassNotFoundException e) {
            logger.error("Could not connection to db", e);
            e.printStackTrace();
        }
    }

    /**
     * Represents singleton pattern that gets the
     * only instance of {@code ConnectionFactory}.
     *
     * @return single instance of {@code ConnectionFactory}.
     */
    public static ConnectionFactory getInstance() {
        return instance;
    }

    /**
     * Gets an instance of {@code Connection}.
     *
     * @return an instance of {@code Connection}.
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        return connection;
    }
}
