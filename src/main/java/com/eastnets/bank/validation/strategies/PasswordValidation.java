package main.java.com.eastnets.bank.validation.strategies;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.StringUtils;
import main.java.com.eastnets.bank.validation.ValidationStrategy;

public class PasswordValidation implements ValidationStrategy<Customer> {
    public void validate(Customer customer) {
        if(StringUtils.isBlankOrNull(customer.getPassword())) {
            throw new InvalidFieldException("Password must not be empty");
        }if(customer.getPassword().length() < 6) {
            throw new InvalidFieldException("Password must be at least 6 characters");
        }
    }
}
