package by.huryanchyk.servlets;

import by.huryanchyk.entity.User;
import by.huryanchyk.service.Pager;
import by.huryanchyk.service.UserService;
import by.huryanchyk.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The {@code UserServlet} class represents  servlet used as
 * controller for view data from database.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    Pager pager = new Pager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pager.setDefaultParams();
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserServiceImpl userService = (UserServiceImpl) getServletContext().getAttribute("userService");

        if (request.getParameter("recordsPerPage") != null) {
            pager.setRecordsPerPage(Integer.parseInt(request.getParameter("recordsPerPage")));
        }
        if (request.getParameter("sortedMethod") != null){
            pager.setSortedMethod(request.getParameter("sortedMethod"));
        }

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            pager.setCurrentPage(Integer.parseInt(request.getParameter("page")));
        }

        int recordsPerPage = pager.getRecordsPerPage();
        currentPage = pager.getCurrentPage();
        String sortedMethod = pager.getSortedMethod();
        long noOfRecords = userService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        List<User> currentUsersList = userService.currentUsersList((currentPage - 1) * recordsPerPage, recordsPerPage, sortedMethod);


        request.setAttribute("noOfRecords", noOfRecords);
        request.setAttribute("currentUsersList", currentUsersList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("sortedMethod", sortedMethod);
        request.setAttribute("recordsPerPage", recordsPerPage);

        RequestDispatcher view = request.getRequestDispatcher("views/displayUser.jsp");
        view.forward(request, response);
    }
}

