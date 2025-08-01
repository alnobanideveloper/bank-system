package main.java.com.eastnets.bank.validation.strategies;


import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.constants.BusinessConstants;
import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.validation.ValidationStrategy;

public class validateBalance implements ValidationStrategy<Account> {

    @Override
    public void validate(Account account) {
        if(account.getBalance()<= BusinessConstants.MIN_BALANCE){
            throw new InvalidFieldException("Balance must be greater than 200");
        }
    }
}
