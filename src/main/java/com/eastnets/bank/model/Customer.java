package main.java.com.eastnets.bank.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
 private String phoneNumber;
 private String nationalId;
 private String name;
 private String email;
 private String address;
 private Branch branch;
 private String password;
 private LocalDate openedAt;
 private List<Account> accounts;



    private Customer(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.nationalId = builder.nationalId;
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.branch = builder.branch;
        this.password = builder.password;
        this.accounts = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public LocalDate getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(LocalDate openedAt) {
        this.openedAt = openedAt;
    }


    @Override
    public String toString() {
        return "name is :" + name
                + "\nphone number is : " + phoneNumber
                + "\nnationalID is : " + nationalId
                + "\naddress is : " + address
                + "\nemail is : " + email;
    }

    public static class Builder{
        private String phoneNumber;
        private String nationalId;
        private String name;
        private String email;
        private String address;
        private Branch branch;
        private String password;

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(String Address){
            this.address = Address;
            return this;
        }

        public Builder setNationalId(String nationalId) {
            this.nationalId = nationalId;
            return this;
        }

        public Builder setBranch(Branch branch) {
            this.branch = branch;
            return this;
        }


        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }


    }


}
