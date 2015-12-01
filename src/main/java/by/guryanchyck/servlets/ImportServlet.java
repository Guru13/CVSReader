package by.guryanchyck.servlets;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Alexey Guryanchyck on 30.08.2015.
 *
 * The {@code ImportServlet} class represents  servlet used as
 * controller for importing data in database.
 */
@WebServlet(name = "ImportServlet", urlPatterns = "/import")
public class ImportServlet extends HttpServlet {
    private final int columnLength = 5;
    private UserDAO dao;

    /**
     * Carries out http-servlet's request in the case of {@code post} request.
     *
     * @param request  is the http-servlet's request.
     * @param response is the http-sevlet's response.
     * @throws ServletException    if servlet error occurs.
     * @throws java.io.IOException if input or output error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream in = request.getInputStream();
        BufferedInputStream bf = new BufferedInputStream(in);
        StringBuffer data = new StringBuffer();

        int bit;
        while ((bit = bf.read()) != -1) {
            data.append((char) bit);
        }

        String allData = new String(data);
        String[] dataArray = allData.split("\n");

        if (dataArray.length <= 6) {
            request.setAttribute("message", "empty");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/importContacts.jsp");
            dispatcher.forward(request, response);
        }

        dao = new UserDAO();
        for (int i = 4; i < dataArray.length - 1; i++) {
            String[] columns = dataArray[i].split(";");
            if (columns.length == columnLength) {
                String name = columns[0];
                String surname = columns[1];
                String login = columns[2];
                String email = columns[3];
                String phoneNumber = columns[4];
                User user = new User(name, surname, login, email, phoneNumber);
                dao.add(user);
            } else {
                RequestDispatcher error = request.getRequestDispatcher("views/error.jsp");
                error.forward(request, response);
            }
        }
        response.sendRedirect("views/successImport.jsp");
    }

    /**
     * It is performed when the servlet stops its existence.
     * It used to carry out finalizing actions.
     */
    @Override
    public void destroy() {
        super.destroy();
        dao.close();
    }
}
