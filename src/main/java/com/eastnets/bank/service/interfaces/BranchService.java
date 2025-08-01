package main.java.com.eastnets.bank.service.interfaces;

import main.java.com.eastnets.bank.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    public List<Branch> getAllBranches(String swiftCode);
    public Optional<Branch> getBranchByNumber(int branchNumber);
}
