package com.test.testproject.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Manuel Lezameta
 */
public final class ConnectionFactory {

    public static final String URL = "jdbc:mysql://localhost:3306/example_schema";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    private ConnectionFactory() {
    }
    
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
