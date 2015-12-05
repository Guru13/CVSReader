package by.guryanchyck.service;

import by.guryanchyck.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexei Guryanchyck
 */
public interface ImportService {

     void importData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
