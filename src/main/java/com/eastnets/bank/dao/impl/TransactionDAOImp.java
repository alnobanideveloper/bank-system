package main.java.com.eastnets.bank.dao.impl;

import main.java.com.eastnets.bank.dao.TransactionDAO;
import main.java.com.eastnets.bank.model.Transaction;
import main.java.com.eastnets.bank.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDAOImp implements TransactionDAO {

    @Override
    public List<Transaction> getAllTransactionsForAccount(int accountNumber) throws SQLException {
        String sql = "SELECT * FROM transaction WHERE source = ? OR destination = ?";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, accountNumber);
            stmt.setInt(2, accountNumber);

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Transaction transaction = new Transaction(
                        result.getDouble("amount"),
                        result.getInt("destination"),
                        result.getInt("source"),
                        result.getInt("transaction_id")
                );
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    @Override
    public Optional<Transaction> getTransaction(int transactionID) throws SQLException {
        String sql = "SELECT * FROM transaction WHERE transaction_id = ?";
        Optional<Transaction> transaction = Optional.empty();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, transactionID);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                transaction = Optional.of(new Transaction(
                        result.getDouble("amount"),
                        result.getInt("destination"),
                        result.getInt("source"),
                        result.getInt("transaction_id")
                ));
            }
        } catch (SQLException e) {
            throw new Error("Operation failed: " + e.getMessage());
        }

        return transaction;
    }

    @Override
    public Optional<Transaction> addTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transaction (source, destination, amount) VALUES (?, ?, ?)";
        Optional<Transaction> transactionOptional = Optional.empty();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, transaction.getSourceNumber());
            stmt.setInt(2, transaction.getDestinationNumber());
            stmt.setDouble(3, transaction.getAmount());

            int result = stmt.executeUpdate();
            if (result > 0) {
                transactionOptional = Optional.of(transaction);
            }
        } catch (SQLException e) {
            throw new Error("Operation failed: " + e.getMessage());
        }

        return transactionOptional;
    }

    @Override
    public List<Transaction> getAllTransactionsByCustomer(int customerID)  throws SQLException {
        return null; // Not implemented yet
    }
}
