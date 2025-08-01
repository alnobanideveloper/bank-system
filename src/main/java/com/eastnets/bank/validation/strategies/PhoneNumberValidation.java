package main.java.com.eastnets.bank.validation.strategies;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.StringUtils;
import main.java.com.eastnets.bank.validation.ValidationStrategy;

public class PhoneNumberValidation implements ValidationStrategy<Customer> {

    @Override
    public void validate(Customer customer) {
        if(StringUtils.isBlankOrNull(customer.getPhoneNumber())) {
            throw new InvalidFieldException("Phone number must not be empty");
        } if(customer.getPhoneNumber().length() < 10) {
            throw new InvalidFieldException("Phone number must be at least 10 digits");
        }
    }
}
