package main.java.com.eastnets.bank.ui;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.model.Account;
import main.java.com.eastnets.bank.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final ServiceContainer serviceContainer;
    private final Customer customer;

    public CustomerMenu(ServiceContainer serviceContainer, Customer customer) {
        this.serviceContainer = serviceContainer;
        this.customer = customer;
    }

    public void mainMenu() {
        if (getAllAccounts().isEmpty()) {
            System.out.println("You don't have any accounts. Please create one.");
            createAccount();
        }

        while (true) {
            MenuHelper.printOptions("Customer Menu",
                    "1. Create Account",
                    "2. View Accounts",
                    "3. Withdraw",
                    "4. Deposit",
                    "5. Transfer",
                    "6. Delete Account",
                    "7. Exit");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    createAccount();
                    break;
                case "3":
                    performWithdraw();
                    break;
                case "2":
                    showAllAccounts();
                    break;
                case "4":
                    performDeposit();
                    break;
                case "5":
                    performTransfer();
                    break;
                case "6":
                    deleteAccount();
                    break;
                case "7":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void createAccount() {
        double initialBalance = InputHandler.readPositiveDouble(scanner, "Enter the initial balance: ");

        Optional<String> accountType = getAccountType();
        if (accountType.isEmpty()) return;

        Account newAccount = new Account(accountType.get(), initialBalance, customer.getNationalId());
        try {
            serviceContainer.accountService.createAccount(newAccount);
            System.out.println("Account created successfully.");
        } catch (InvalidFieldException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    private void deleteAccount() {
        List<Account> accounts = showAllAccounts();
        int idx = InputHandler.readIndex(scanner, "Choose an account to delete: ", accounts.size());
        Account account = accounts.get(idx);

        try {
            int deletedCount = serviceContainer.accountService.deleteAccount(account);
            System.out.println(deletedCount == 1 ? "Account deleted successfully." : "Account was not deleted.");
        } catch (InvalidFieldException e) {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }

    private void performWithdraw() {
        List<Account> accounts = showAllAccounts();
        int idx = InputHandler.readIndex(scanner, "Choose an account to withdraw from: ", accounts.size());
        if (idx == -1) return;

        Account account = accounts.get(idx);
        double amount = InputHandler.readPositiveDouble(scanner, "Enter amount to withdraw: ");

        try {
            double remaining = serviceContainer.accountService.withdraw(account, amount);
            System.out.println("Withdrawal successful. Remaining balance: " + remaining);
        } catch (RuntimeException ex) {
            System.out.println("Error during withdrawal: " + ex.getMessage());
        }
    }

    private void performDeposit() {
        List<Account> accounts = showAllAccounts();
        int idx = InputHandler.readIndex(scanner, "Choose an account to deposit into: ", accounts.size());
        if (idx == -1) return;

        Account account = accounts.get(idx);
        double amount = InputHandler.readPositiveDouble(scanner, "Enter amount to deposit: ");

        try {
            double newBalance = serviceContainer.accountService.deposit(account, amount);
            System.out.println("Deposit successful. New balance: " + newBalance);
        } catch (RuntimeException ex) {
            System.out.println("Error during deposit: " + ex.getMessage());
        }
    }

    private void performTransfer() {
        List<Account> accounts = showAllAccounts();
        int index = chooseAccountIndex(accounts);
        if (index == -1) return;

        Account fromAccount = accounts.get(index);
        double amount = InputHandler.readPositiveDouble(scanner, "Enter amount to transfer: ");

        Optional<Account> toAccountOpt = getDestinationAccount();
        if (toAccountOpt.isEmpty()) {
            System.out.println("Transfer cancelled.");
            return;
        }

        processTransfer(fromAccount, toAccountOpt.get(), amount);
    }

    private int chooseAccountIndex(List<Account> accounts) {
        return InputHandler.readIndex(scanner, "Choose the account to transfer from: ", accounts.size());
    }

    private Optional<Account> getDestinationAccount() {
        while (true) {
            int toAccountNumber = InputHandler.readInt(scanner, "Enter the destination account number (or -1 to cancel): ");
            if (toAccountNumber == -1) {
                return Optional.empty();
            }

            Optional<Account> accountOpt = serviceContainer.accountService.getAccountByNumber(toAccountNumber);
            if (accountOpt.isPresent()) {
                return accountOpt;
            } else {
                System.out.println("No such account exists. Please try again.");
            }
        }
    }

    private void processTransfer(Account fromAccount, Account toAccount, double amount) {
        try {
            double newBalance = serviceContainer.accountService.transfer(amount, fromAccount, toAccount);
            System.out.println("Transfer successful. Your new balance: " + newBalance);
        } catch (RuntimeException ex) {
            System.out.println("Transfer failed: " + ex.getMessage());
        }
    }

    private Optional<String> getAccountType() {
        MenuHelper.printOptions("Choose your account type:", "1. Current", "2. Savings");
        String choice = scanner.nextLine().trim();

        if ("1".equals(choice)) {
            return Optional.of("current");
        } else if ("2".equals(choice)) {
            return Optional.of("savings");
        } else {
            System.out.println("Invalid account type choice. Aborting account creation.");
            return Optional.empty();
        }
    }

    private List<Account> showAllAccounts() {
        List<Account> accounts = getAllAccounts();
        MenuHelper.printList("accounts", accounts);
        return accounts;
    }


    private List<Account> getAllAccounts() {
        return serviceContainer.accountService.getAllAccounts(customer.getNationalId());
    }
}
