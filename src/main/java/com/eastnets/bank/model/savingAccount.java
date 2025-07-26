package main.java.com.eastnets.bank.model;

public class savingAccount extends Account{
    private final int minimumAmmmount = 200;
    private final float interestRate = 0.25f;
    private final int maxTransactions = 10;


    public savingAccount( int accountNo, double balance){
        super("savings" , accountNo, balance);
    }

    public int getMaxTransactions() {
        return maxTransactions;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public int getMinimumAmmmount() {
        return minimumAmmmount;
    }



}
