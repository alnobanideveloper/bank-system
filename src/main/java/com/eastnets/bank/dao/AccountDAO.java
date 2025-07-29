package main.java.com.eastnets.bank.dao;

import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountDAO {
    public List<Account> getAllAccounts(String customerID) throws SQLException;
    public Optional<Account> getAccount(int accountNumber)throws SQLException;
    public int editAccount(int accountNumber, Account account)throws SQLException;
    public int deleteAllAccounts(String customerID)throws SQLException;
    public int deleteAccount(int accountNumber)throws SQLException;
    public Optional<Account> createAccount(Account account)throws SQLException;

}
