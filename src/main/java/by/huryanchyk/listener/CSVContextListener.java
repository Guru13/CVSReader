package by.huryanchyk.listener;


import by.huryanchyk.db.DAOManagerFactory;
import by.huryanchyk.db.DAOManagerJDBCFactory;
import by.huryanchyk.service.ImportServiceImpl;
import by.huryanchyk.service.UserServiceImpl;
import org.apache.log4j.Logger;

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
public class CSVContextListener implements ServletContextListener {


    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private ImportServiceImpl.Importer importer;
    private Thread thread = null;

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        UserServiceImpl userService = new UserServiceImpl();
        ImportServiceImpl importService = new ImportServiceImpl();

        importer = importService.new Importer(importService.getUsersQueueString());

        thread = new Thread(importer);
        logger.debug("Starting thread: " + thread);
        thread.start();
        logger.debug("Background process successfully started.");

        DAOManagerFactory daoManagerFactory = new DAOManagerJDBCFactory();

        userService.setDaoManagerFactory(daoManagerFactory);
        importService.setUserService(userService);

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("importService", importService);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("Stopping thread: " + thread);
        if (thread != null) {
            importer.terminate();
            try {
                thread.join();
                logger.debug("Thread successfully stopped.");
            } catch (InterruptedException e) {
               logger.error("Exception ", e);
            }
        }
    }
}
