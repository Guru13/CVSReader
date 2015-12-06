package by.guryanchyck.service;

import by.guryanchyck.entity.User;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Alexei Guryanchyck
 */
public class ImportServiceImpl implements ImportService {

    private BlockingQueue<String[]> usersQueueString = new ArrayBlockingQueue<>(1024);

    private UserService userService;
    private Importer importer;

    public ImportServiceImpl() {
        importer = new Importer(usersQueueString);
    }


    public void addTextToQueue(String[] dataArray) throws InterruptedException {

        usersQueueString.put(dataArray);
        new Thread(importer).start();
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
                    userService.addUser(dataArray);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
