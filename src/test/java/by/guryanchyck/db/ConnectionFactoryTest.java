package by.guryanchyck.db;

import by.guryanchyck.dao.UserDAO;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Alexey Guryanchyck on 30.08.2015.
 */
public class ConnectionFactoryTest {
    private static ConnectionFactory connectionFactory;

    @BeforeClass
    public static void initConnectionFactory() {
        connectionFactory = ConnectionFactory.getInstance();
    }

    @Test
    public void getConnectionTest() throws SQLException, ClassNotFoundException {
        for (int i = 0; i < 20; i++) {
            Assert.assertNotNull(connectionFactory.getConnection());
        }
    }




}