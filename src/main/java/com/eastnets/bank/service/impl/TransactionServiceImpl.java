package main.java.com.eastnets.bank.service.impl;

import main.java.com.eastnets.bank.dao.TransactionDAO;
import main.java.com.eastnets.bank.model.Transaction;
import java.util.List;

public class TransactionServiceImpl {
    TransactionDAO transactionDAO;

    public TransactionServiceImpl(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }


    public List<Transaction> getAllTransactions(int accountNumber){
        try {
            return transactionDAO.getAllTransactionsForAccount(accountNumber);
        }catch (Exception e){
            throw new RuntimeException("Database error" );
        }
    }
}
