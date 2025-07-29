package main.java.com.eastnets.bank.dao;
import main.java.com.eastnets.bank.model.Branch;
import main.java.com.eastnets.bank.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BranchDAO {
    public List<Branch> getAllBranchesForBank(String swift) throws SQLException;
    public Optional<Branch> getBranch(int branchNumber) throws SQLException;
//    public void editBranch(int branchNumber , Branch branch) ;
}
