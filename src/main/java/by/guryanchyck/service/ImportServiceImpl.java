package by.guryanchyck.service;

import by.guryanchyck.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Alexei Guryanchyck
 */
public class ImportServiceImpl implements ImportService {

    private final int columnCount = 5;
    private BlockingQueue<User> usersQueue = new ArrayBlockingQueue<User>(1024);

    private UserService userService;

    public void addUserToDB(String[] dataArray) {
        for (int i = 4; i < dataArray.length - 1; i++) {
            String[] columns = dataArray[i].split(";");

            if (columns.length != columnCount) {
                continue;
            }
            String name = columns[0];
            String surname = columns[1];
            String login = columns[2];
            String email = columns[3];
            String phoneNumber = columns[4];
            User user = new User(name, surname, login, email, phoneNumber);
            userService.addUser(user);
        }
    }

    public void addUserToQueue(String[] dataArray) throws InterruptedException {
        for (int i = 4; i < dataArray.length - 1; i++) {
            String[] columns = dataArray[i].split(";");

            if (columns.length != columnCount) {
                continue;
            }
            String name = columns[0];
            String surname = columns[1];
            String login = columns[2];
            String email = columns[3];
            String phoneNumber = columns[4];
            User user = new User(name, surname, login, email, phoneNumber);
            usersQueue.put(user);
        }
    }

    public void addUserToBase() {
        Importer importer = new Importer(usersQueue);
        new Thread(importer).start();
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    class Importer implements Runnable {

        protected BlockingQueue<User> blockingQueue;

        public Importer(BlockingQueue<User> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    User user = blockingQueue.take();
                    userService.addUser(user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
