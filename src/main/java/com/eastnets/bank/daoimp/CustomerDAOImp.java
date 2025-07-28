package main.java.com.eastnets.bank.daoimp;

import main.java.com.eastnets.bank.dao.CustomerDAO;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO {
    private Connection connection;

    public CustomerDAOImp(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO " +
                "CUSTOMER(nationalId , name , email , address , phone , branch_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customer.getNationalId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5 , customer.getPhoneNumber());
            pstmt.setInt(6, customer.getBranch().getNumber());
            pstmt.executeUpdate();

            System.out.println("successfully inserted");
        } catch (SQLException e) {
            System.out.println("operation failed " + e.getMessage());
        }
    }
        @Override
    public Customer getCustomerByID(String id) {
        String sql = "SELECT * FROM CUSTOMER WHERE nationalId = ?";
        Customer customer = null;
        try(PreparedStatement stmt =  connection.prepareStatement(sql)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                customer = new Customer.Builder()
                        .setName(rs.getString("name"))
                        .setEmail(rs.getString("email"))
                        .setPhoneNumber(rs.getString("phone"))
                        .setNationalId(rs.getString("nationalId"))
                        .setAddress(rs.getString("address"))
                        .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return customer; // returns null if not found
        }

    @Override
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM CUSTOMER WHERE email = ?";
        Customer customer = null;
        try(PreparedStatement stmt =  connection.prepareStatement(sql)){
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                customer = new Customer.Builder()
                        .setName(rs.getString("name"))
                        .setEmail(rs.getString("email"))
                        .setPhoneNumber(rs.getString("phone"))
                        .setNationalId(rs.getString("nationalId"))
                        .setAddress(rs.getString("address"))
                        .build();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void deleteCustomerByID(String id) {
        String sql = "DELETE FROM CUSTOMER WHERE nationalId = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("No customer found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("operation failed " + e.getMessage());
        }
    }

    @Override
    public void editCustomerByID(String id, Customer customer) {
        String sql = "UPDATE CUSTOMER SET name = ?, email = ?, address = ?, phone = ?, branch_number = ? WHERE nationalId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName()); // name instead of first/last name
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setInt(5, customer.getBranch().getNumber()); // assuming Branch has branch_number
            stmt.setString(6, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("No customer found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }


    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER";
        Customer customer = null;
        try(PreparedStatement stmt =  connection.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                customer = new Customer.Builder()
                        .setName(rs.getString("name"))
                        .setEmail(rs.getString("email"))
                        .setPhoneNumber(rs.getString("phone"))
                        .setNationalId(rs.getString("nationalId"))
                        .setAddress(rs.getString("address"))
                        .build();
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
