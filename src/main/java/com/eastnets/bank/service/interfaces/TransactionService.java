package main.java.com.eastnets.bank.service.interfaces;

import main.java.com.eastnets.bank.model.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions(int accountNumber);
}
