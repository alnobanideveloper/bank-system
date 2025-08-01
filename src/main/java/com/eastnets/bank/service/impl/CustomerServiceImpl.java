package main.java.com.eastnets.bank.service.impl;

import main.java.com.eastnets.bank.dao.CustomerDAO;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.validatorUtil;
import main.java.com.eastnets.bank.service.interfaces.CustomerSerivce;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerSerivce {
    CustomerDAO customerDAO ;

    public  CustomerServiceImpl(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        Optional<Customer> customer = Optional.empty();
        try{
            customer = customerDAO.getCustomerByID(id);
        }catch(SQLException e){
            throw new RuntimeException("Unable to fetch customer data. Please try again.", e);
        }
        return customer;
    }

    @Override
    public int editCustomer(Customer customer) {
        int rowsUpdated = 0;
        validatorUtil.validateCustomer( customer);
        try{
            rowsUpdated = customerDAO.editCustomerByID(customer.getNationalId() , customer);
        }catch(SQLException e){
            throw new RuntimeException("Unable to fetch customer data. Please try again.", e);
        }
        return rowsUpdated;
    }

    //a service to delete a customer , if the rowsDeleted = 0 so the id is wrong
    @Override
    public int deleteCustomer(Customer customer) {
        int rowsDeleted = 0;
        try{
            rowsDeleted = customerDAO.deleteCustomerByID(customer.getNationalId());
        }catch(SQLException e){
            throw new RuntimeException("Unable to fetch customer data. Please try again.", e);
        }
        return rowsDeleted;
    }
}
