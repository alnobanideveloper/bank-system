package main.java.com.eastnets.bank.app;

import main.java.com.eastnets.bank.dao.impl.BankDAOImp;
import main.java.com.eastnets.bank.dao.impl.BranchDAOImp;
import main.java.com.eastnets.bank.dao.impl.CustomerDAOImp;

import main.java.com.eastnets.bank.model.Bank;
import main.java.com.eastnets.bank.model.Branch;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Test {

    public static void main(String[] args) throws SQLException {

        CustomerDAOImp test = new CustomerDAOImp();

        Customer c = new
                Customer.Builder().
                setPhoneNumber("0796975").
                setAddress("Jordan amman").
                setBranch(new Branch("amman", "branch1", 1, "1"))
                .setName("ahmad alnobani")
                .setPassword("1234")
                .setEmail("saadalbani@gmal.com")
                .setNationalId("20002")
                .build();

//        Optional<Customer> customer = test.createCustomer(c);
//        customer.ifPresent(System.out::println);

        Optional<Customer> customer = test.getCustomerByID("20002");
        customer.ifPresent(System.out::println);
        //edit the old customer
//           c.setName("saad alnobani");
//           c.setAddress("usa");
//           System.out.println("\nThe edited new customer : ");
//           test.editCustomerByID("200002323", c);
//
//           //print the new edited customer
//           System.out.println(test.getCustomerByID("200002323"));
//
//           //get all cusotmers test
//           List<Customer> customers = test.getAllCustomers();
//           System.out.println(customers);
//
//           //delete a custoemr with non existing id
//           test.deleteCustomerByID("2002323");
//           System.out.println("all customers for branch 1");
//           System.out.println(test.getAllCustomersOfBranch(1));
//           System.out.println("finished");
//
//           //BankDAOImp Test
//           BankDAOImp bankDAOImp = new BankDAOImp();
//
//           //get all banks tets
//           List<Bank> banks = bankDAOImp.getAllBanks();
//           System.out.println("\nbanks that are available : ");
//           for (Bank b : banks) {
//               System.out.println(b.getName());
//           }
//
//           //get a bank by swift number
//           System.out.println(bankDAOImp.getBankBySwift("2"));
//
//
//           //Branch test
//           BranchDAOImp branchDAOImp = new BranchDAOImp();
//           System.out.println(branchDAOImp.getAllBranchesForBank("1211"));
//           //close the conection with database
//       }
    }
}
