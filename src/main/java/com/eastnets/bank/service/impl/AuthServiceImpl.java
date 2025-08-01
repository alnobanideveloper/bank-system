package main.java.com.eastnets.bank.service.impl;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.dao.CustomerDAO;
import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.service.interfaces.AuthSerivce;
import main.java.com.eastnets.bank.validation.ValidationStrategy;
import main.java.com.eastnets.bank.validation.Validator;
import main.java.com.eastnets.bank.validation.strategies.*;
import main.java.com.eastnets.bank.util.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AuthServiceImpl implements AuthSerivce {
    CustomerDAO customerDAO ;

    public  AuthServiceImpl(CustomerDAO customerDAO  ){
        this.customerDAO = customerDAO; //implement it like this way to prevent tight coupling
    }


    //if its empty, then its invalid credentials
    @Override
    public Optional<Customer> signIn(String email, String password) {
        try{
            return customerDAO.getCustomerByEmailAndPassword(email , password);
        } catch (SQLException e){
            throw new RuntimeException("Database error" , e);
        }
    }


    @Override
    public Optional<Customer> registerCustomer(Customer customer) {
        validatorUtil.validateCustomer(customer);
        checkIfCustomerExists(customer);

        try{
           return  customerDAO.createCustomer(customer);
        }catch(SQLException e){
            throw new RuntimeException("Database error" , e);
        }
    }

    private void checkIfCustomerExists(Customer customer){
        checkIfCustomerExistsById(customer.getNationalId());
        checkIfCustomerExistsByEmail(customer.getEmail());
    }

    private void checkIfCustomerExistsById(String nationalId) {
        try {
            boolean exists = customerDAO.getCustomerByID(nationalId).isPresent();
            if (exists)
                throw new InvalidFieldException("Customer with this national ID already exists.");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to check customer existence in the database.", e);
        }
    }

    private void checkIfCustomerExistsByEmail(String email) {
        try {
            boolean exists = customerDAO.getCustomerByEmail(email).isPresent();
            if (exists)
                throw new InvalidFieldException("Customer with this email already exists.");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to check customer existence in the database.", e);
        }
    }

}
