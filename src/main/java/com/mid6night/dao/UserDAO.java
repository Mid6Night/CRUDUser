package com.mid6night.dao;

import com.mid6night.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();

    User getUser(long id);

    void deleteUser(long id);

    void addUser(User user);

    void updateUser(User user);
}
