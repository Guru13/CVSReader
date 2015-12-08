package by.huryanchyk.service;

import org.apache.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p>
 * The class implements methods for conversion data in file
 * and importing it into database
 */
public class ImportServiceImpl implements ImportService {

    private final static Logger LOGGER = Logger.getLogger(ImportServiceImpl.class);


    private BlockingQueue<String[]> usersQueueString = new ArrayBlockingQueue<>(1);

    private UserService userService;

    public ImportServiceImpl() {
    }

    @Override
    public void addTextToQueue(String[] dataArray) throws InterruptedException {
        usersQueueString.put(dataArray);
    }

    public BlockingQueue<String[]> getUsersQueueString() {
        return usersQueueString;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Created by Alexey Guryanchyck on 05.12.2015.
     * <p>
     * The class for importing data from queue to database
     */
    public class Importer implements Runnable {

        private BlockingQueue<String[]> blockingQueueString;

        public Importer(BlockingQueue<String[]> queue) {
            this.blockingQueueString = queue;
        }

        private volatile boolean running = true;

        public void terminate() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    LOGGER.debug("processing...");
                    String[] dataArray = blockingQueueString.take();
                    userService.addAllUsers(dataArray);
                } catch (InterruptedException e) {
                    LOGGER.error("interrupted exception", e);
                    running = false;
                }
            }
        }
    }
}
