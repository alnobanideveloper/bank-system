package main.java.com.eastnets.bank.dao;

import main.java.com.eastnets.bank.model.Transaction;

import java.util.List;

public interface TransactionDAO {
    public List<Transaction> getAllTransactionsByCustomer(int customerID);
    public List<Transaction> getAllTransactionsForAccount(int accountNumber);
    public Transaction getTransaction(int transactionID);
    public void addTransaction(Transaction transaction);
    public void editTransaction(int transactionNumber, Transaction transaction);
    public void deleteTransaction(int transactionNumber);
}

