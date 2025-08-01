package main.java.com.eastnets.bank.ui;
import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.model.Bank;
import main.java.com.eastnets.bank.model.Branch;

import java.util.List;

public class MenuHelper {

    //to print list of options
    //...options = an array of string
    public static void printOptions(String title, String... options) {
        System.out.println("\n=== " + title + " ===");
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Enter your choice: ");
    }

    public static <T> void printList(String title, List<T> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No " + title.toLowerCase() + " available.");
            return;
        }
        System.out.println("\nAvailable " + title + ":");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ": " + items.get(i));
        }
    }

}
