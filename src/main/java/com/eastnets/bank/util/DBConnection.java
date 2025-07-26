package main.java.com.eastnets.bank.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBConnection {

    // Single shared Connection instance
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {}

    // Static block to initialize the connection once
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

    public static void main(String[] args) {
        String sql = "SELECT * FROM eastnetsbank.bank;";

        // Get the singleton connection instance
        Connection conn = DBConnection.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("swift_code");
                String name = rs.getString("name");
                String email = rs.getString("address");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
