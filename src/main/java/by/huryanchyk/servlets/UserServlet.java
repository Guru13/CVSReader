package by.huryanchyk.servlets;

import by.huryanchyk.entity.User;
import by.huryanchyk.service.UserService;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = (UserService) getServletContext().getAttribute("userService");

        if (request.getParameter("recordsPerPage") != null) {
            userService.setRecordsPerPage(Integer.parseInt(request.getParameter("recordsPerPage")));
        }
        if (request.getParameter("sortedMethod") != null){
            userService.setSortedMethod(request.getParameter("sortedMethod"));
        }

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int recordsPerPage = userService.getRecordsPerPage();
        String sortedMethod = userService.getSortedMethod();

        List<User> currentUsersList = userService.currentUsersList((page - 1) * recordsPerPage, recordsPerPage, sortedMethod);

        long noOfRecords = userService.getNoOfRecords();

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("noOfRecords", noOfRecords);
        request.setAttribute("currentUsersList", currentUsersList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("sortedMethod", sortedMethod);
        request.setAttribute("recordsPerPage", recordsPerPage);

        RequestDispatcher view = request.getRequestDispatcher("views/displayUser.jsp");
        view.forward(request, response);
    }
}

