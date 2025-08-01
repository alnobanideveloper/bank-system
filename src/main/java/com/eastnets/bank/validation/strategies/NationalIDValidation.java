package main.java.com.eastnets.bank.validation.strategies;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.StringUtils;
import main.java.com.eastnets.bank.validation.ValidationStrategy;

public class NationalIDValidation implements ValidationStrategy<Customer> {

    @Override
    public void validate(Customer customer) {
        if(StringUtils.isBlankOrNull(customer.getNationalId())) {
            throw new InvalidFieldException("NationalID must not be empty");
        }
    }
}
