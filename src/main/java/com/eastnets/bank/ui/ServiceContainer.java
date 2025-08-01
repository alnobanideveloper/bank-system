package main.java.com.eastnets.bank.ui;

import main.java.com.eastnets.bank.service.interfaces.*;

public class ServiceContainer {
    public final AuthSerivce authService;
        public final CustomerSerivce customerService;
        public final BankService bankService;
        public final BranchService branchService;
        public final AccountService accountService;

        public ServiceContainer(AuthSerivce authService, CustomerSerivce customerService, BankService bankService, BranchService branchService, AccountService accountService) {
            this.authService = authService;
            this.customerService = customerService;
            this.bankService = bankService;
            this.branchService = branchService;
            this.accountService = accountService;

    }

}
