package com.mid6night.Services;

import com.mid6night.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getUser(long id);

    void deleteUser(long id);

    void addUser(User user);

    void updateUser(User user);

    User findUser(String name, String password);
}
