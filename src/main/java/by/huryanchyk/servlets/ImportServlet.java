package by.huryanchyk.servlets;


import by.huryanchyk.service.ImportService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The {@code ImportServlet} class represents  servlet used as
 * controller for importing data in database.
 */
@WebServlet(name = "ImportServlet", urlPatterns = "/import")
public class ImportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ImportService importService = (ImportService)getServletContext().getAttribute("importService");
        ServletInputStream in = request.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(in);

        String[] dataArray = readDataToArray(bis);

        if (dataArray.length <= 6) {
            request.setAttribute("message", "empty");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/importContacts.jsp");
            dispatcher.forward(request, response);
        }

        try {
            importService.addTextToQueue(dataArray);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.sendRedirect("views/successImport.jsp");
    }

    private String[] readDataToArray(BufferedInputStream bis) throws IOException {
        StringBuffer data = new StringBuffer();
        int bit;
        while ((bit = bis.read()) != -1) {
            data.append((char) bit);
        }
        return new String(data).split("\n");
    }
}
