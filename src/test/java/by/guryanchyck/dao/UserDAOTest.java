package by.guryanchyck.dao;

import by.guryanchyck.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alexey Guryanchyck on 30.08.2015.
 */
public class UserDAOTest {


    @Test
    public void testAdd() {
        User testUser = new User("testName", "testSurname", "testLogin", "testEmail", "testPhoneNumber");
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.add(testUser);
        assertTrue(userDAO.userExist(testUser));
    }

    @Test
    public void testAddUpdate() {
        User testUser1 = new User("testName1", "testSurname1", "testLogin", "testEmail1", "testPhoneNumber1");
        User testUser3 = new User("testName3", "testSurname3", "testLogin", "testEmail3", "testPhoneNumber3");
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.add(testUser1);
        userDAO.add(testUser3);
        assertFalse(userDAO.userExist(testUser1));
        assertTrue(userDAO.userExist(testUser3));
    }


    @Test
    public void testGetNoOfRecords() throws Exception {
        UserDAOImpl userDAO = new UserDAOImpl();
        List<User> userList = userDAO.getAllUsers();
        assertTrue(userList.size() == userDAO.getNoOfRecords());

    }
}