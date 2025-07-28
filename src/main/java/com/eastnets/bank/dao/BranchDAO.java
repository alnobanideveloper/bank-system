package main.java.com.eastnets.bank.dao;
import main.java.com.eastnets.bank.model.Branch;
import main.java.com.eastnets.bank.model.Customer;
import java.util.List;

public interface BranchDAO {
    public List<Customer> getAllCustomersOfBranch(int branchNumber);
    public List<Branch> getAllBranches();
    public Branch getBranch(int branchNumber);
    public Branch getBranchByName(String branchName);
    public void editBranch(int branchNumber , Branch branch);
}
