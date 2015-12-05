package by.guryanchyck.servlets;

import by.guryanchyck.entity.User;
import by.guryanchyck.service.ImportService;
import by.guryanchyck.service.ImportServiceImpl;
import by.guryanchyck.service.UserService;
import by.guryanchyck.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Alexey Guryanchyck on 30.08.2015.
 * <p/>
 * The {@code ImportServlet} class represents  servlet used as
 * controller for importing data in database.
 */
@WebServlet(name = "ImportServlet", urlPatterns = "/import")
public class ImportServlet extends HttpServlet {


    /**
     * Carries out http-servlet's request in the case of {@code post} request.
     *
     * @param request  is the http-servlet's request.
     * @param response is the http-sevlet's response.
     * @throws ServletException    if servlet error occurs.
     * @throws java.io.IOException if input or output error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ImportService importService = (ImportService)getServletContext().getAttribute("importService");

        importService.importData(request, response);

        response.sendRedirect("views/successImport.jsp");
    }

    /**
     * It is performed when the servlet stops its existence.
     * It used to carry out finalizing actions.
     */
    @Override
    public void destroy() {
        super.destroy();
//        dao.close();
    }
}
