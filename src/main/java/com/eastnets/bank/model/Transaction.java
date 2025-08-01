package main.java.com.eastnets.bank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private Integer sourceNumber;
    private Integer destinationNumber;
    private double amount;
    private String type;
    private LocalDateTime created_at;


    public Transaction(){}

    public Transaction(double amount, Integer destinationNumber, Integer sourceNumber,  String type) {
        this.amount = amount;
        this.destinationNumber = destinationNumber;
        this.sourceNumber = sourceNumber;
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Integer getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(Integer sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public Integer getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(Integer destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
