package main.java.com.eastnets.bank.dao;

import main.java.com.eastnets.bank.model.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TransactionDAO {
    public List<Transaction> getAllTransactionsByCustomer(int customerID) throws SQLException;
    public List<Transaction> getAllTransactionsForAccount(int accountNumber) throws SQLException;
    public Optional<Transaction> getTransaction(int transactionID) throws SQLException;
    public Optional<Transaction> addTransaction(Transaction transaction) throws SQLException;
}

