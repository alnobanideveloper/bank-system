package main.java.com.eastnets.bank.dao;

import main.java.com.eastnets.bank.model.Customer;

import java.util.List;

public interface CustomerDAO {
    public void createCustomer(Customer customer);
    public Customer getCustomerByID(String id);
    public  Customer getCustomerByEmail(String email);
    public void deleteCustomerByID(String id);
    public void editCustomerByID(String id, Customer customer);
    public List <Customer> getAllCustomers();

}
