package com.mid6night.dao;

import com.mid6night.DBConnect;
import com.mid6night.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;
    private static UserDao userDao;

    private UserDao() {
        this.connection = DBConnect.getMysqlConnection();
        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table users\n" +
                "(\n" +
                "\tid bigint auto_increment,\n" +
                "\tname varchar(255) null,\n" +
                "\tage varchar(255) null,\n" +
                "\tconstraint users_pk\n" +
                "\t\tprimary key (id)\n" +
                ");");
        stmt.close();
    }

    public boolean deleteUser(long id) {
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM users WHERE id = '" + id + "';";
            stmt.executeUpdate(query);
            stmt.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                users.add(user);
            }
            resultSet.close();
            stmt.close();
        } catch (Exception e) {
            return users;
        }
        return users;
    }

    public boolean addUser(User user) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("insert into users (name, age) values ('" +
                    user.getName() + "', '" +
                    user.getAge() + "')");
            stmt.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public User getUser(long id) {
        User user = new User();
        try {
            user.setId(id);
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users where id = '" + id + "'");
            ResultSet resultSet = stmt.getResultSet();
            resultSet.next();
            user.setAge(resultSet.getInt(3));
            user.setName(resultSet.getString(2));
            resultSet.close();
            stmt.close();
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public boolean updateUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("update users set name = ?, age = ? where id = ?");
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setLong(3, user.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
