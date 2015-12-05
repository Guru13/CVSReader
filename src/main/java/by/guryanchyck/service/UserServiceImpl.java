package by.guryanchyck.service;

import by.guryanchyck.dao.UserDAO;
import by.guryanchyck.dao.UserDAOImpl;
import by.guryanchyck.entity.User;
import java.util.List;

/**
 * Created by Alexei Guryanchyck
 */
public class UserServiceImpl implements UserService{



    private UserDAO userDAO = new UserDAOImpl();
//    int page = 1;
//    int recordsPerPage = 10;
//    long noOfRecords;
//    int noOfPages;


    public void addUser(User user) {
        userDAO.add(user);
    }

    public String getSortedMethod(String sortedMethod){
        if (sortedMethod == null){
            sortedMethod = "name";
        }
        return sortedMethod;
    }

//    public void setPage(HttpServletRequest request){
//        if (request.getParameter("page") != null) {
//            page = Integer.parseInt(request.getParameter("page"));
//        }
//    }
//    public List<User> getValues(){
//        return userDAO.values((page - 1) * recordsPerPage, recordsPerPage, sortedMethod);
//    }

//    public long getNoOfRecords(){
//        return  userDAO.getNoOfRecords();
//    }
//    public int getNoOfPages(){
//        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//    }
//    public void setAttributes(HttpServletRequest request, List<User> listUsers, int noOfPages, int page, String sortedMethod){
//        request.setAttribute("userList", listUsers);
//        request.setAttribute("noOfPages", noOfPages);
//        request.setAttribute("currentPage", page);
//        request.setAttribute("sortedMethod", sortedMethod);
//    }

//    public void go(HttpServletRequest request){
//        getSortedMethod(request);
//        setPage(request);
//        List<User> listUsers = getValues();
//        noOfPages = getNoOfPages();
//        sortedMethod = getSortedMethod(request);
//        setAttributes(request, listUsers, noOfPages, page, sortedMethod);
//    }

    public List<User> values(int offset, int noOfRecords, String compareMethod, UserDAO userDAO) {
        return userDAO.values(offset, noOfRecords, compareMethod);
    }

    @Override
    public void addAllUsers(List<User> users) {
        userDAO.addAllUsers(users);
    }
}
