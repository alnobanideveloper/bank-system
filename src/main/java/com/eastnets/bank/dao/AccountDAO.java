package main.java.com.eastnets.bank.dao;

import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.model.Customer;
import java.util.List;

interface AccountDAO {
    public List<Account> getAllAccounts(int customerID);
    public Account getAccount(int accountNumber);
    public void editAccount(int accountNumber, Account account);
    public void deleteAllAccounts(int customerID);
    public void deleteAccount(int accountNumber);
    public void createAccount(Account account);

}
