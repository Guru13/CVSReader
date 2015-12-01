package by.guryanchyck.db;

import by.guryanchyck.service.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alexey Guryanchyck on 30.08.2015.
 *
 * The  class represents an ability to connect with database.
 */
public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();
    private final Settings settings;


    private ConnectionFactory() {
        settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
        } catch (ClassNotFoundException e) {
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
