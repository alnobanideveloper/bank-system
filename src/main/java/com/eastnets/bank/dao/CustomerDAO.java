package main.java.com.eastnets.bank.dao;

import main.java.com.eastnets.bank.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    public Optional<Customer> createCustomer(Customer customer) throws SQLException;
    public Optional<Customer> getCustomerByID(String id) throws SQLException;
    public Optional<Customer> getCustomerByEmailAndPassword(String email , String password) throws SQLException;
    public Optional<Customer> getCustomerByEmail(String email) throws SQLException;
    public int deleteCustomerByID(String id) throws SQLException;
    public int editCustomerByID(String id, Customer customer) throws SQLException;
    public List <Customer> getAllCustomers() throws SQLException;
    public List<Customer> getAllCustomersOfBranch(int branchNumber) throws SQLException;


}
