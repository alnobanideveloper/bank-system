package main.java.com.eastnets.bank.model;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private int sourceNumber;
    private int destinationNumber;
    private double amount;

    public Transaction() {

    }

    public double getAmount() {
        return amount;
    }

    public Transaction(double amount, int destinationNumber, int sourceNumber, int id) {
        this.amount = amount;
        this.destinationNumber = destinationNumber;
        this.sourceNumber = sourceNumber;
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public int getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(int sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public int getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(int destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private LocalDate created_at;

}
