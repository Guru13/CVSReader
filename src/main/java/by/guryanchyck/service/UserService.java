package by.guryanchyck.service;

import by.guryanchyck.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexei Guryanchyck
 */
public interface UserService {

    void addUser(User user);

    String getSortedMethod(HttpServletRequest request);

    void setPage(HttpServletRequest request);

    void go(HttpServletRequest request);
}
