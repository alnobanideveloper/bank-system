package main.java.com.eastnets.bank.validation;

import main.java.com.eastnets.bank.model.Customer;

import java.util.List;

public class Validator<T> {
    private final List<ValidationStrategy<T>> strategies;

    public Validator(List<ValidationStrategy<T>> strategies) {
        this.strategies = strategies;
    }

    public void validate(T object) {
        for (ValidationStrategy<T> strategy : strategies) {
            strategy.validate(object);
        }
    }
}


