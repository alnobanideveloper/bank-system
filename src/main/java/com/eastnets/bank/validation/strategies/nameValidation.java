package main.java.com.eastnets.bank.validation.strategies;

import com.mysql.cj.util.StringUtils;
import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.validation.ValidationStrategy;

public class nameValidation implements ValidationStrategy<Customer> {

    @Override
    public void validate(Customer customer) {
        if(StringUtils.isNullOrEmpty(customer.getName())) {
            throw new InvalidFieldException("Customer name cant be empty");
        }
    }
}
