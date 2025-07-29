package main.java.com.eastnets.bank.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

    protected String nationalID;
    protected String accountType;
    protected int accountNo;
    protected double balance;
    protected LocalDate openedDate;

    // No-argument constructor (needed by frameworks)
    public Account() {}

    // All-arguments constructor
    public Account( String accountType, int accountNo, double balance , String nationalID ) {
        this.accountType = accountType;
        this.accountNo = accountNo;
        this.balance = balance;
        this.nationalID = nationalID;
        this.openedDate  = LocalDate.now();
    }

    // Getters and Setters
    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountNo() {
        return accountNo;
    }


    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

}
