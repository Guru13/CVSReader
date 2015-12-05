package by.guryanchyck.servlets;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.dao.UserDAOImpl;
import by.guryanchyck.entity.User;
import by.guryanchyck.service.UserService;
import by.guryanchyck.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexey Guryanchyck on 30.08.2015.
 * <p/>
 * The {@code UserServlet} class represents  servlet used as
 * controller for view data from database.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    /**
     * Carries out http-servlet's request in the case of {@code post} request.
     *
     * @param request  is the http-servlet's request.
     * @param response is the http-sevlet's response.
     * @throws ServletException    if servlet error occurs.
     * @throws java.io.IOException if input or output error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    /**
     * Carries out http-servlet's request in the case of {@code get} request.
     *
     * @param request  is the http-servlet's request.
     * @param response is the http-sevlet's response.
     * @throws ServletException    if servlet error occurs.
     * @throws java.io.IOException if input or output error occurs.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = (UserService) getServletContext().getAttribute("userService");
        String sortedMethod = userService.getSortedMethod(request.getParameter("sortedMethod"));



        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        UserDAO userDAO = (UserDAO) getServletContext().getAttribute("userDAO");

//        List<User> listUsers = userDAO.values((page - 1) * recordsPerPage, recordsPerPage, sortedMethod);
        List<User> listUsers = userService.values((page - 1) * recordsPerPage, recordsPerPage, sortedMethod, userDAO);
        long noOfRecords = userDAO.getNoOfRecords();

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("userList", listUsers);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("sortedMethod", sortedMethod);

        RequestDispatcher view = request.getRequestDispatcher("views/displayUser.jsp");
        view.forward(request, response);
    }

    /**
     * It is performed when the servlet stops its existence.
     * It used to carry out finalizing actions.
     */
    @Override
    public void destroy() {
        super.destroy();
//        userDAO.close();
    }
}

