package by.huryanchyk.db;

import by.huryanchyk.service.Settings;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p>
 * The  class represents an ability to connect with database.
 */
public class ConnectionFactory {

    private final static Logger logger = Logger.getLogger(ConnectionFactory.class);


    /**
     * Gets an instance of {@code Connection}.
     *
     * @return an instance of {@code Connection}.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName(Settings.value("jdbc.driver_class"));

        return DriverManager.getConnection(Settings.value("jdbc.url"), Settings.value("jdbc.username"), Settings.value("jdbc.password"));
    }
}
