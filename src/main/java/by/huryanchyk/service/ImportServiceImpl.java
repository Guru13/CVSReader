package by.huryanchyk.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The class implements methods for conversion data in file
 * and importing it into database
 */
public class ImportServiceImpl implements ImportService {

    private BlockingQueue<String[]> usersQueueString = new ArrayBlockingQueue<>(1);

    private UserService userService;

    public ImportServiceImpl() {
        Importer importer = new Importer(usersQueueString);
        new Thread(importer).start();
    }

    @Override
    public void addTextToQueue(String[] dataArray) throws InterruptedException {
        usersQueueString.put(dataArray);
    }

    @Override
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Created by Alexey Guryanchyck on 05.12.2015.
     * <p/>
     * The class for importing data from queue to database
     */
    class Importer implements Runnable {

        private BlockingQueue<String[]> blockingQueueString;

        public Importer(BlockingQueue<String[]> queue) {
            this.blockingQueueString = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String[] dataArray = blockingQueueString.take();
                    userService.addAllUsers(dataArray);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
