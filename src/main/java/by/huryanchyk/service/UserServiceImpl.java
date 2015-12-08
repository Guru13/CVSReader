package by.huryanchyk.service;

import by.huryanchyk.dao.DAOManager;
import by.huryanchyk.dao.DAOManagerFactory;
import by.huryanchyk.entity.User;
import by.huryanchyk.exceptions.DaoException;
import by.huryanchyk.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p>
 * The class has methods
 * for manipulation with user's data.
 */
public class UserServiceImpl implements UserService {


    private DAOManagerFactory daoManagerFactory;

    @Override
    public List<User> currentUsersList(int offset, int noOfRecords, String compareMethod) {

        try (DAOManager daoManager = daoManagerFactory.getDAOManager()) {
            daoManager.beginTransaction();
            try {
                List<User> users = daoManager.getUserDAO().currentUsersList(offset, noOfRecords, compareMethod);
                daoManager.commitTransaction();
                return users;
            } catch (Exception e) {
                daoManager.rollbackTransaction();
                throw new ServiceException("what ", e);
            }
        } catch (DaoException e) {
            throw new ServiceException("currentUsersList method has throwen an exception", e);
        } catch (Exception e) {
            throw new ServiceException("autocloseable exception ", e);
        }
    }

    @Override
    public void addAllUsers(String[] dataArray) {

        List<User> users = new ArrayList<>();

        for (int i = 4; i < dataArray.length - 1; i++) {
            String[] columns = dataArray[i].split(";");
            int columnCount = 5;
            if (columns.length != columnCount) {
                continue;
            }
            String name = columns[0];
            String surname = columns[1];
            String login = columns[2];
            String email = columns[3];
            String phoneNumber = columns[4];
            User user = new User(name, surname, login, email, phoneNumber);

            users.add(user);
        }
        try (DAOManager daoManager = daoManagerFactory.getDAOManager()) {
            daoManager.beginTransaction();
            try {
                daoManager.getUserDAO().addAllUsers(users);
                daoManager.commitTransaction();
            } catch (DaoException e) {
                daoManager.rollbackTransaction();
                throw new ServiceException("addAllUsers method has throwen an exception", e);
            }
        } catch (Exception e) {
            throw new ServiceException("autocloseable exception ", e);
        }
    }

    @Override
    public long getNoOfRecords() {
        try (DAOManager daoManager = daoManagerFactory.getDAOManager()) {
            return daoManager.getUserDAO().getNoOfRecords();
        } catch (DaoException e) {
            throw new ServiceException("getNoOfRecords has throwen an exception", e);
        } catch (Exception e) {
            throw new ServiceException("autocloseable exception ", e);
        }
    }

    public void setDaoManagerFactory(DAOManagerFactory daoManagerFactory) {
        this.daoManagerFactory = daoManagerFactory;
    }
}
