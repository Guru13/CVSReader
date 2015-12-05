package by.guryanchyck.service;

import by.guryanchyck.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created by Alexei Guryanchyck
 */
public class ImportServiceImpl implements ImportService {

    private final int columnCount = 5;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;


    public String readData(BufferedInputStream bis) throws IOException {
        StringBuffer data = new StringBuffer();
        int bit;
        while ((bit = bis.read()) != -1) {
            data.append((char) bit);
        }
        return new String(data);
    }

    public String[] dataToArray(String data) {
        return data.split("\n");
    }

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
            getUserService().addUser(user);
//            userService.addUser(user);
        }
    }


}
