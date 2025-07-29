package main.java.com.eastnets.bank.dao;
import main.java.com.eastnets.bank.model.Bank;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BankDAO {
    public List<Bank> getAllBanks() throws SQLException;
    public Optional<Bank> getBankBySwift(String swift) throws SQLException;

}
