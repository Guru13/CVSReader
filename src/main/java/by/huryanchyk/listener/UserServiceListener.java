package by.huryanchyk.listener;

import by.huryanchyk.dao.UserDAO;
import by.huryanchyk.dao.UserDAOImpl;
import by.huryanchyk.service.ImportService;
import by.huryanchyk.service.ImportServiceImpl;
import by.huryanchyk.service.UserService;
import by.huryanchyk.service.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The class implements methods defined on ServletContextListener.
 */
@WebListener
public class UserServiceListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        UserService userService = new UserServiceImpl();
        ImportService importService = new ImportServiceImpl();
        UserDAO userDAO = new UserDAOImpl();

        userService.setUserDAO(userDAO);
        importService.setUserService(userService);

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("importService", importService);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
