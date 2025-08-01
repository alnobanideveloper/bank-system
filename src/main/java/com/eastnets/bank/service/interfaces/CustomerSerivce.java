package main.java.com.eastnets.bank.service.interfaces;

import main.java.com.eastnets.bank.model.Customer;

import java.util.Optional;

public interface CustomerSerivce {
    public Optional<Customer> getCustomerById(String id);
    public int editCustomer(Customer customer);
    public int deleteCustomer(Customer customer);
}
