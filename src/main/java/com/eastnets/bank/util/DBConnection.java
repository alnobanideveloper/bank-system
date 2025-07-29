package main.java.com.eastnets.bank.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static DBConnection instance;
    private static String url;
    private static String user;
    private static String password;

    private DBConnection() {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("main/resources/db.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database properties");
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get DB connection");
        }
    }
}
