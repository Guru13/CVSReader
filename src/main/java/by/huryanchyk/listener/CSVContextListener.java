package by.huryanchyk.listener;


import by.huryanchyk.dao.DAOManagerFactory;
import by.huryanchyk.dao.DAOManagerJDBCFactory;
import by.huryanchyk.service.ImportServiceImpl;
import by.huryanchyk.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p>
 * The class implements methods defined on ServletContextListener.
 */
@WebListener
public class CSVContextListener implements ServletContextListener {


    private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private ImportServiceImpl.Importer importer;
    private Thread thread = null;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        UserServiceImpl userService = new UserServiceImpl();
        ImportServiceImpl importService = new ImportServiceImpl();

        importer = importService.new Importer(importService.getUsersQueueString());

        thread = new Thread(importer);
        LOGGER.debug("Starting thread: " + thread);
        thread.start();
        LOGGER.debug("Background process successfully started.");

        DAOManagerFactory daoManagerFactory = new DAOManagerJDBCFactory();

        userService.setDaoManagerFactory(daoManagerFactory);
        importService.setUserService(userService);

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("importService", importService);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.debug("Stopping thread: " + thread);
        if (thread != null) {
            importer.terminate();
            try {
                thread.join();
                LOGGER.debug("Thread successfully stopped.");
            } catch (InterruptedException e) {
                LOGGER.error("Exception ", e);
            }
        }
    }
}
