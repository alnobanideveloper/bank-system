package main.java.com.eastnets.bank.app;

import main.java.com.eastnets.bank.dao.impl.*;
import main.java.com.eastnets.bank.service.impl.*;
import main.java.com.eastnets.bank.service.interfaces.BankService;
import main.java.com.eastnets.bank.service.interfaces.BranchService;
import main.java.com.eastnets.bank.service.interfaces.TransactionService;
import main.java.com.eastnets.bank.ui.ServiceContainer;
import main.java.com.eastnets.bank.ui.StartupMenu;

public class Main {
    public static void main(String[] args) {
        //initialze DAOs
        BankDAOImp bankDAO = new BankDAOImp();
        CustomerDAOImp customerDAO = new CustomerDAOImp();
        BranchDAOImp branchDAO = new BranchDAOImp();
        AccountDAOImp accountDAOImp = new AccountDAOImp();
        TransactionDAOImp transactionDAOImp = new TransactionDAOImp();

        //initialize Services
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerDAO);
        BankServiceImpl bankService = new BankServiceImpl(bankDAO);
        BranchServiceImpl branchService = new BranchServiceImpl(branchDAO);
        AuthServiceImpl authService = new AuthServiceImpl(customerDAO);
        TransactionServiceImpl transactionService = new TransactionServiceImpl(transactionDAOImp);
        AccountServiceImpl accountService = new AccountServiceImpl(accountDAOImp , transactionDAOImp );
        ServiceContainer serviceContainer = new ServiceContainer(authService , customerService , bankService , branchService , accountService);

        //initialize the UI
        StartupMenu startupMenu = new StartupMenu(serviceContainer);
        //start the app
        startupMenu.run();
    }
}
