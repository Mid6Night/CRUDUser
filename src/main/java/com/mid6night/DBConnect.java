package com.mid6night;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection getMysqlConnection() {
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
}
