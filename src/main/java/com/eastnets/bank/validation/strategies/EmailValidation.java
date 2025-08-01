package main.java.com.eastnets.bank.validation.strategies;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.StringUtils;
import main.java.com.eastnets.bank.validation.ValidationStrategy;

public class EmailValidation implements ValidationStrategy<Customer> {
    public void validate(Customer customer) {
        if(StringUtils.isBlankOrNull(customer.getEmail())) {
            throw new InvalidFieldException("Email must not be empty");
        }
    }
}

