package main.java.com.eastnets.bank.dao.impl;

import main.java.com.eastnets.bank.dao.BranchDAO;
import main.java.com.eastnets.bank.util.DBConnection;
import main.java.com.eastnets.bank.model.Branch;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BranchDAOImp implements BranchDAO {

    @Override
    public Optional<Branch> getBranch(int branchNumber) throws SQLException {
        String sql = "SELECT * FROM branch WHERE branch_number = ?";
        Optional<Branch> branch = Optional.empty();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, branchNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                branch = Optional.of( new Branch(
                        rs.getString("location"),
                        rs.getString("name"),
                        rs.getInt("branch_number"),
                        rs.getString("bank_swift")
                ));
            }
        }

        return branch;
    }

    @Override
    public List<Branch> getAllBranchesForBank(String swift) throws SQLException {
        String sql = "SELECT * FROM branch WHERE bank_swift = ?";
        List<Branch> branches = new ArrayList<>();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, swift);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Branch branch = new Branch(
                        rs.getString("location"),
                        rs.getString("name"),
                        rs.getInt("branch_number"),
                        rs.getString("bank_swift")
                );
                branches.add(branch);
            }

        }
        return branches;
    }
}
