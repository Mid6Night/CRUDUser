package com.mid6night.dao;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {

    public  UserDAO getDao() throws IOException {
        UserDAO userDAO = null;
        Properties properties = new Properties();

        properties.load(UserDaoFactory.class
                .getClassLoader().getResourceAsStream("dao.properties"));

        switch (properties.getProperty("daotype")) {
            case "hibernate":
                userDAO = UserHibernateDAO.getInstance();
                break;
            case "jdbc":
                userDAO = UserJdbcDAO.getInstance();
                break;
        }
        return userDAO;
    }
}
