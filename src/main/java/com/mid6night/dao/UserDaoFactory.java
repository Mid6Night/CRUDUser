package com.mid6night.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO getDao() throws IOException {
        UserDAO userDAO = null;
        Properties properties = new Properties();

        InputStream in = UserDaoFactory.class.getClassLoader().getResourceAsStream("src/main/resources/dao.properties") ;
        properties.load(in);

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
