package by.guryanchyck.service;

import by.guryanchyck.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created by Alexei Guryanchyck
 */
public interface ImportService {

     String[] dataToArray(String data);

     String readData(BufferedInputStream bis) throws IOException;

     void addUserToDB(String[] dataArray);

     void setUserService(UserService userService);
}
