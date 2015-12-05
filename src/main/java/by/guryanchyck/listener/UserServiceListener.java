package by.guryanchyck.listener;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.dao.UserDAOImpl;
import by.guryanchyck.service.ImportService;
import by.guryanchyck.service.ImportServiceImpl;
import by.guryanchyck.service.UserService;
import by.guryanchyck.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Alexei Guryanchyck
 */

@WebListener
public class UserServiceListener implements ServletContextListener {

    private UserService userService;
    private ImportService importService;
    private UserDAO userDAO;


    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        userService = new UserServiceImpl();
        importService = new ImportServiceImpl();
        userDAO = new UserDAOImpl();
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("importService", importService);
        servletContext.setAttribute("userDAO", userDAO);

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
