package com.mid6night.Services;

import com.mid6night.dao.UserDAO;
import com.mid6night.dao.UserHibernateDAO;

import com.mid6night.entity.User;

import java.util.List;

public class UserServiceHibernate implements UserService {
    private UserDAO userDao;
    private static UserServiceHibernate userServiceHibernate;

    private UserServiceHibernate() {
        userDao = UserHibernateDAO.getInstance();
    }

    public static UserServiceHibernate getInstance() {
        if (userServiceHibernate == null) {
            userServiceHibernate = new UserServiceHibernate();
        }
        return userServiceHibernate;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User findUser(String name, String password) {
        return userDao.findUser(name, password);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
