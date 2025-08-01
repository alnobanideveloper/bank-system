package main.java.com.eastnets.bank.service.impl;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.dao.AccountDAO;
import main.java.com.eastnets.bank.dao.TransactionDAO;
import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.model.Transaction;
import main.java.com.eastnets.bank.service.interfaces.AccountService;
import main.java.com.eastnets.bank.validation.ValidationStrategy;
import main.java.com.eastnets.bank.validation.Validator;
import main.java.com.eastnets.bank.validation.strategies.validateBalance;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    AccountDAO accountDao ;
    TransactionDAO transactionDao ;
    public AccountServiceImpl(AccountDAO accountDao , TransactionDAO transactionDao) {
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public Optional<Account> getAccountByNumber(int accountNumber) {
        try{
            return accountDao.getAccount(accountNumber);
        } catch(SQLException e){
            throw new RuntimeException("Database Error");
        }
    }

    @Override
    public Optional<Account> createAccount(Account account) {
        validateAccount(account);
        try{
            return accountDao.createAccount(account);
        } catch(SQLException e){
            throw new RuntimeException("Database Error");
        }
    }


    @Override
    public int editAccount(Account account) {
        validateAccount(account);
        try{
            return accountDao.editAccount(account.getAccountNo() , account);
        } catch(SQLException e){
            throw new RuntimeException("Database Error");
        }
    }

    @Override
    public int deleteAccount(Account account) {
        try{
            return accountDao.deleteAccount(account.getAccountNo());
        } catch(SQLException e){
           throw new RuntimeException("Database Error");
        }
    }

    @Override
    public int deleteAllAccounts(String customerId) {
        try{
            return accountDao.deleteAllAccounts(customerId);
        } catch(SQLException e){
            throw new RuntimeException("Database Error");
        }
    }

    @Override
    public List<Account> getAllAccounts(String  customerId) {
        try{
            return accountDao.getAllAccounts(customerId);
        } catch(SQLException e){
            throw new RuntimeException("Database Error");
        }
    }

    @Override
    public double withdraw(Account account , double amount) {
        checkTransferAmount(amount);
        Account updatedAccount = updateBalance(account, amount);
        try{
            accountDao.editAccount(account.getAccountNo() , updatedAccount);
            transactionDao.addTransaction(new Transaction(amount ,null , updatedAccount.getAccountNo(),"withdraw" ));
        } catch(SQLException e){
            throw new RuntimeException("Database Error");
        }
        return updatedAccount.getBalance();

    }

    @Override
    public double deposit(Account account , double amount) {
        checkTransferAmount(amount);
       double newBalance = account.getBalance() + amount;
       account.setBalance(newBalance);
        try{
             accountDao.editAccount(account.getAccountNo() , account);
            transactionDao.addTransaction(new Transaction(amount ,account.getAccountNo() , null,"deposite" ));

        } catch(SQLException e){
            throw new RuntimeException("Database Error");
        }
        return newBalance;
    }

    @Override
    public double transfer(double amount, Account fromAccount, Account toAccount) {
        checkTransferAmount(amount);

        // Deduct from source
        Account updatedFrom = updateBalance(fromAccount, amount);
        validateAccount(updatedFrom);
        // Add to destination
        toAccount.setBalance(toAccount.getBalance() + amount);

        try {
            accountDao.editAccount(fromAccount.getAccountNo(), updatedFrom);
            accountDao.editAccount(toAccount.getAccountNo(), toAccount);

            // One transaction with both source and destination
            transactionDao.addTransaction(new Transaction(amount, toAccount.getAccountNo(), fromAccount.getAccountNo(), "transfer"));

        } catch (SQLException e) {
            throw new RuntimeException("Database Error", e);
        }

        return updatedFrom.getBalance();
    }



    private Account updateBalance(Account account, double delta) {
        Account updated = new Account(account);
        updated.setBalance(account.getBalance() - delta);
        validateAccount(updated);
        return updated;
    }

    private void checkTransferAmount(double ammount){
        if(ammount < 0)
            throw new InvalidFieldException("Transfer amount must be greater than 0");
    }


    private void validateAccount(Account account){
        List<ValidationStrategy> validations = List.of(new validateBalance());
        Validator validator = new Validator(validations);
        validator.validate(account);

    }
}
