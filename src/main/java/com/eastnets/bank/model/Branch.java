package main.java.com.eastnets.bank.model;
import java.util.List;

public class Branch {
    private int number;
    private String name;
    private String address;
    private List<Customer> customers;
    private String swiftCode;



    public Branch() {}

    public Branch(List<Customer> customers, String address, String name, int number) {
        this.customers = customers;
        this.address = address;
        this.name = name;
        this.number = number;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "number=" + number  +
                ", name='" + name + '\'' +
                ", email='" +   address + '\'' +
                ", customers='" + customers + '\'' +
                '}';
        }
    }
