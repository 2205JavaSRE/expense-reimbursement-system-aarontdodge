package com.project1.dao;

import com.project1.models.Reimbursement;
import com.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao {
    @Override
    public void insertReimbursement(Reimbursement newRequest) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO project1.reimbursement (approved, category, amount, username)\n" +
                "VALUES \n" +
                "\t(FALSE, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newRequest.getCategory());
            ps.setDouble(2, newRequest.getAmount());
            ps.setString(3, newRequest.getRequesterUsername());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reimbursement> getReimbursements() {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM project1.reimbursement;";
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"),
                                                    rs.getString("request_time"),
                                                    rs.getBoolean("reviewed"),
                                                    rs.getString("category"),
                                                    rs.getDouble("amount"),
                                                    rs.getBoolean("approved"),
                                                    rs.getString("username"));
                reimbursementList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public List<Reimbursement> getPendingReimbursements() {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM project1.reimbursement WHERE reviewed = FALSE;";
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"),
                                                    rs.getString("request_time"),
                                                    rs.getBoolean("reviewed"),
                                                    rs.getString("category"),
                                                    rs.getDouble("amount"),
                                                    rs.getBoolean("approved"),
                                                    rs.getString("username"));
                reimbursementList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public List<Reimbursement> getMyPending(String username) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM project1.reimbursement WHERE reviewed = FALSE AND username = ?;";
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"),
                                                    rs.getString("request_time"),
                                                    rs.getBoolean("reviewed"),
                                                    rs.getString("category"),
                                                    rs.getDouble("amount"),
                                                    rs.getBoolean("approved"),
                                                    rs.getString("username"));
                reimbursementList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public List<Reimbursement> getReimbursementsByUsername(String username) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM project1.reimbursement WHERE username = ?;";
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"),
                                                    rs.getString("request_time"),
                                                    rs.getBoolean("reviewed"),
                                                    rs.getString("category"),
                                                    rs.getDouble("amount"),
                                                    rs.getBoolean("approved"),
                                                    rs.getString("username"));
                reimbursementList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public List<Reimbursement> getReviewedReimbursements(String requestingEmployee) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM project1.reimbursement WHERE reviewed = TRUE AND username = ?;";
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, requestingEmployee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"),
                                                    rs.getString("request_time"),
                                                    rs.getBoolean("reviewed"),
                                                    rs.getString("category"),
                                                    rs.getDouble("amount"),
                                                    rs.getBoolean("approved"),
                                                    rs.getString("username"));
                reimbursementList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public List<Reimbursement> getReviewedReimbursements() {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM project1.reimbursement WHERE reviewed = TRUE;";
        List<Reimbursement> reimbursementList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r = new Reimbursement(rs.getInt("reimbursement_id"),
                                                    rs.getString("request_time"),
                                                    rs.getBoolean("reviewed"),
                                                    rs.getString("category"),
                                                    rs.getDouble("amount"),
                                                    rs.getBoolean("approved"),
                                                    rs.getString("username"));
                reimbursementList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public void updateRequest(int id, boolean approved) {
        String sql = "UPDATE project1.reimbursement SET approved = ?, reviewed = TRUE WHERE reimbursement_id = ?";
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, approved);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
