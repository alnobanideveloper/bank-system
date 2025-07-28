package main.java.com.eastnets.bank.app;

import main.java.com.eastnets.bank.daoimp.CustomerDAOImp;

import main.java.com.eastnets.bank.model.Branch;
import main.java.com.eastnets.bank.model.Customer;
import main.java.com.eastnets.bank.util.DBConnection;
import java.sql.Connection;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        CustomerDAOImp test = new CustomerDAOImp(conn);

        Customer c = new
                Customer.Builder().
                setPhoneNumber("0795697475").
                setAddress("Jordan amman").
                setBranch(new Branch("amman" , "branch1" , 1))
                .setName("ahmad alnobani")
                .setPassword("1234")
                .setEmail("saadalnobani@gmail.com")
                .setNationalId("200002323")
                .build();

        test.createCustomer(c);
        System.out.println(test.getCustomerByID("200002323"));
        //edit the old customer
        c.setName("saad alnobani");
        c.setAddress("usa");
        System.out.println("\nThe edited new customer : ");
        test.editCustomerByID("200002323" , c);

        //print the new edited customer
        System.out.println(test.getCustomerByID("200002323"));

        //get all cusotmers test
        List<Customer> customers = test.getAllCustomers();
        System.out.println(customers);

        //delete a custoemr with non existing id
        test.deleteCustomerByID("2002323");

        DBConnection.closeConnection();
    }
}
