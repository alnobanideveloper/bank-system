package main.java.com.eastnets.bank.service.interfaces;

import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.model.Customer;

import java.sql.SQLException;
import java.util.Optional;

public interface AuthSerivce {
    public Optional<Customer> signIn(String email, String password) ;
    public Optional<Customer> registerCustomer(Customer customer);
}
