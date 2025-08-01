package main.java.com.eastnets.bank.validation;

import main.java.com.eastnets.bank.model.Customer;

public interface ValidationStrategy<T> {
    public void validate(T object);
}
