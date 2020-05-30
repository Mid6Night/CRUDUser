package com.mid6night.Services;

import com.mid6night.dao.UserDAO;
import com.mid6night.dao.UserDaoFactory;
import com.mid6night.entity.User;

import java.io.IOException;
import java.util.List;

public class Service implements UserService {
    private UserDAO userDAO;
    private static Service service;

    private Service() {
        try {
            userDAO = new UserDaoFactory().getDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Service getInstance() {
        if (service == null){
            service = new Service();
        }
        return service;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
}
