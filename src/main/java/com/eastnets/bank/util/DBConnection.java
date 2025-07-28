package main.java.com.eastnets.bank.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

    // Single shared Connection instance
    private static final Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {}

    static {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("main/resources/db.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load DB properties or connect");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
