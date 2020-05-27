package com.mid6night.Services;

import com.mid6night.dao.UserDao;
import com.mid6night.dao.UserJdbcDAO;
import com.mid6night.entity.User;

import java.util.List;

public class UserServiceJdbc implements UserService {
    private UserDao userDao;
    private static UserServiceJdbc userServiceJdbc;

    private UserServiceJdbc() {
        userDao = UserJdbcDAO.getInstance();
    }

    public static UserServiceJdbc getUserServiceJdbc() {
        if (userServiceJdbc == null){
            userServiceJdbc = new UserServiceJdbc();
        }
        return userServiceJdbc;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
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
