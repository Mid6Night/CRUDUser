package com.mid6night.dao;

import com.mid6night.DBConnect;
import com.mid6night.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDao {
    private Connection connection;
    private static UserJdbcDAO userJdbcDAO;

    private UserJdbcDAO() {
        this.connection = DBConnect.getMysqlConnection();
        createTable();
    }

    public static UserJdbcDAO getInstance() {
        if (userJdbcDAO == null) {
            userJdbcDAO = new UserJdbcDAO();
        }
        return userJdbcDAO;
    }

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table users\n" +
                    "(\n" +
                    "\tid bigint auto_increment,\n" +
                    "\tname varchar(255) null,\n" +
                    "\tage varchar(255) null,\n" +
                    "\tconstraint users_pk\n" +
                    "\t\tprimary key (id)\n" +
                    ");");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(long id) {
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            String query = "DELETE FROM users WHERE id = '" + id + "';";
            stmt.executeUpdate(query);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
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

        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

    public void addUser(User user) {
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.execute("insert into users (name, age) values ('" +
                    user.getName() + "', '" +
                    user.getAge() + "')");
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public User getUser(long id) {
        User user = new User();
        try (Statement stmt = connection.createStatement()) {
            user.setId(id);
            stmt.execute("select * from users where id = '" + id + "'");
            ResultSet resultSet = stmt.getResultSet();
            resultSet.next();
            user.setAge(resultSet.getInt(3));
            user.setName(resultSet.getString(2));
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("update users set name = ?, age = ? where id = ?");) {
            connection.setAutoCommit(false);
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setLong(3, user.getId());
            stmt.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
