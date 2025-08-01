package main.java.com.eastnets.bank.service.impl;

import main.java.com.eastnets.bank.dao.BranchDAO;
import main.java.com.eastnets.bank.model.Branch;
import main.java.com.eastnets.bank.service.interfaces.BranchService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BranchServiceImpl implements BranchService {
    BranchDAO branchDAO ;

    public BranchServiceImpl(BranchDAO branchDAO){
        this.branchDAO = branchDAO;
    }

    @Override
    public List<Branch> getAllBranches(String swiftCode) {
        try{
          return branchDAO.getAllBranchesForBank(swiftCode);
      }catch(SQLException e){
          throw new RuntimeException("database Error");
      }
    }

    @Override
    public Optional<Branch> getBranchByNumber(int branchNumber) {
        try{
            return branchDAO.getBranch(branchNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
