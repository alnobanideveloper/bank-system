package main.java.com.eastnets.bank.service.impl;

import main.java.com.eastnets.bank.dao.BankDAO;
import main.java.com.eastnets.bank.model.Bank;
import main.java.com.eastnets.bank.service.interfaces.BankService;

import java.sql.SQLException;
import java.util.List;

public class BankServiceImpl implements BankService {
    BankDAO bankDAO;
    public BankServiceImpl(BankDAO bankDAO){
        this.bankDAO = bankDAO;
    }

    public List<Bank> getAllBanks(){
        try{
            return bankDAO.getAllBanks();
        }catch(SQLException ex){
            throw new RuntimeException("database Error");
        }
    }

}
