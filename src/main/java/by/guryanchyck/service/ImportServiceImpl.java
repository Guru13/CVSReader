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
    private UserService userService = new UserServiceImpl();

    private String readData(HttpServletRequest request) throws IOException {

        ServletInputStream in = request.getInputStream();
        BufferedInputStream bf = new BufferedInputStream(in);
        StringBuffer data = new StringBuffer();

        int bit;
        while ((bit = bf.read()) != -1) {
            data.append((char) bit);
        }
        return new String(data);
    }

    private String[] dataToArray(String data) {
        return data.split("\n");
    }

    private void addUserToDB(String[] dataArray, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for (int i = 4; i < dataArray.length - 1; i++) {
            String[] columns = dataArray[i].split(";");
            if(columns.length != columnCount){
                continue;
            }
            if (columns.length == columnCount) {
                String name = columns[0];
                String surname = columns[1];
                String login = columns[2];
                String email = columns[3];
                String phoneNumber = columns[4];
                User user = new User(name, surname, login, email, phoneNumber);
                userService.addUser(user);

            } else {
                RequestDispatcher error = request.getRequestDispatcher("views/error.jsp");
                error.forward(request, response);
            }
        }
    }

    private void checkFile(String[] dataArray, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (dataArray.length <= 6) {
            request.setAttribute("message", "empty");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/importContacts.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void importData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String allData = readData(request);
        String[] dataArray = dataToArray(allData);
        checkFile(dataArray, request, response);
        addUserToDB(dataArray, request, response);
    }


}
