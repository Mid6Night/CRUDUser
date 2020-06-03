package com.mid6night;

import com.mid6night.entity.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper dbHelper;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://127.0.0.1:3306/example?serverTimezone=Europe/Moscow&user=root&password=root&useSSL=false");        //db type
                    /*append("localhost:").           //host name
                    append("3306/").                //port
                    append("example?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password*/

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/example?serverTimezone=Europe/Moscow&user=root&password=root&useSSL=false");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

}
