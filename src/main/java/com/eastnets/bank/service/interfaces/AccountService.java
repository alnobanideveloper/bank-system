package main.java.com.eastnets.bank.service.interfaces;

import main.java.com.eastnets.bank.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public Optional<Account> getAccountByNumber(int accountNumber);
    public Optional<Account> createAccount(Account account);
    public int editAccount(Account account);
    public int deleteAccount(Account account);
    public int deleteAllAccounts(String customerId);
    public List<Account> getAllAccounts(String customerId);
    public double withdraw(Account account , double amount);
    public double deposit(Account account , double amount);
    public double transfer(double amount ,Account fromAccount , Account toAccount );
}
