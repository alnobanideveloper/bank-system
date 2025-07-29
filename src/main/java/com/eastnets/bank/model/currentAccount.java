package main.java.com.eastnets.bank.model;

public class currentAccount extends Account {
    private final int minAmount = 250;

    public int getMinAmount() {
        return minAmount;
    }

    public currentAccount(int accountNo, double balance , String nationalID ) {
        super("savings" , accountNo, balance, nationalID);
    }

}
