package main.java.com.eastnets.bank.ui;

import main.java.com.eastnets.bank.Exceptions.InvalidFieldException;
import main.java.com.eastnets.bank.model.Bank;
import main.java.com.eastnets.bank.model.Branch;
import main.java.com.eastnets.bank.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StartupMenu {
    private final ServiceContainer serviceContainer;
    private final Scanner scanner = new Scanner(System.in);

    public StartupMenu(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    public void run() {
        while (true) {
            MenuHelper.printOptions("Welcome to the Bank System", "1. Sign Up", "2. Sign In", "0. Exit");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    signUp();
                    break;
                case "2":
                    signIn();
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }



    //to sign in to an account
    //if the authService return an empty customer that means its invalid credentials
    private void signIn() {
        String email = InputHandler.readString(scanner, "Enter your email: ");
        String password = InputHandler.readString(scanner, "Enter your password: ");

        Optional<Customer> customer = serviceContainer.authService.signIn(email, password);
        if (customer.isPresent()) {
            System.out.println("Welcome, " + customer.get().getName());
            new CustomerMenu(serviceContainer, customer.get()).mainMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void signUp() {
        Optional<Bank> selectedBank = selectBank();
        if (selectedBank.isEmpty()) return;

        Optional<Branch> selectedBranch = selectBranch(selectedBank.get());
        if (selectedBranch.isEmpty()) return;

        Optional<Customer> customer = collectCustomerInfo(selectedBranch.get());
        if (customer.isEmpty()) return;

        try {
            serviceContainer.authService.registerCustomer(customer.get());
            System.out.println("Account created successfully!");
        } catch (InvalidFieldException e) {
            System.out.println("Error during sign up: " + e.getMessage());
        }
    }

    private Optional<Bank> selectBank() {
        List<Bank> banks = serviceContainer.bankService.getAllBanks();
        if (banks.isEmpty()) {
            System.out.println("No banks available. Please try later.");
            return Optional.empty();
        }
        MenuHelper.printList("Banks", banks);
        int index = InputHandler.readIndex(scanner, "Choose a bank: ", banks.size());
        return (index == -1) ? Optional.empty() : Optional.of(banks.get(index));
    }

    private Optional<Branch> selectBranch(Bank bank) {
        List<Branch> branches = serviceContainer.branchService.getAllBranches(bank.getSWIFT());
        if (branches.isEmpty()) {
            System.out.println("No branches available for this bank.");
            return Optional.empty();
        }        MenuHelper.printList("Branches", branches);
        int index = InputHandler.readIndex(scanner, "Choose a branch: ", branches.size());
        return (index == -1) ? Optional.empty() : Optional.of(branches.get(index));
    }

    private Optional<Customer> collectCustomerInfo(Branch branch) {
        String email = InputHandler.readString(scanner, "Enter your email: ");
        String password = InputHandler.readString(scanner, "Enter your password: ");
        String nationalId = InputHandler.readString(scanner, "Enter your national ID: ");
        String phoneNumber = InputHandler.readString(scanner, "Enter your phone number: ");
        String name = InputHandler.readString(scanner, "Enter your name: ");
        String address = InputHandler.readString(scanner, "Enter your address: ");

        try {
            return Optional.of(new Customer.Builder()
                    .setEmail(email)
                    .setPassword(password)
                    .setPhoneNumber(phoneNumber)
                    .setAddress(address)
                    .setName(name)
                    .setBranch(branch)
                    .setNationalId(nationalId)
                    .build());
        } catch (InvalidFieldException e) {
            System.out.println("Invalid data: " + e.getMessage());
            return Optional.empty();
        }
    }


}
