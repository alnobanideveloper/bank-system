package main.java.com.eastnets.bank.util;

import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.validation.ValidationStrategy;
import main.java.com.eastnets.bank.validation.Validator;
import main.java.com.eastnets.bank.validation.strategies.*;

import java.util.List;

public class validatorUtil {
    private validatorUtil() {}

    public static void validateCustomer(Customer customer){

        List<ValidationStrategy> strategies = List.of(
                new EmailValidation(),
                new PasswordValidation(),
                new nameValidation(),
                new PhoneNumberValidation(),
                new NationalIDValidation()
        );

        Validator validator = new Validator(strategies);
        validator.validate(customer);

    }

}
