package main.java.com.eastnets.bank.dao.impl;

import main.java.com.eastnets.bank.dao.BankDAO;
import main.java.com.eastnets.bank.model.Bank;
import main.java.com.eastnets.bank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankDAOImp implements BankDAO {

    @Override
    public List<Bank> getAllBanks() {
        String sql = "SELECT * FROM bank";
        List<Bank> banks = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bank bank = new Bank(
                        rs.getString("name"),
                        rs.getString("swift_code"),
                        rs.getString("address")
                );
                banks.add(bank);
            }

        } catch (SQLException ex) {
            System.out.println("Operation failed: " + ex.getMessage());
        }

        return banks;
    }

    @Override
    public Optional<Bank> getBankBySwift(String swift) {
        String sql = "SELECT * FROM bank WHERE swift_code = ?";
        Optional<Bank> bank = Optional.empty();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, swift);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bank = Optional.of(new Bank(
                        rs.getString("name"),
                        rs.getString("swift_code"),
                        rs.getString("address"))
                );
            }

        } catch (SQLException ex) {
            throw new Error("Operation failed: " + ex.getMessage());
        }
        return bank;
    }

}
