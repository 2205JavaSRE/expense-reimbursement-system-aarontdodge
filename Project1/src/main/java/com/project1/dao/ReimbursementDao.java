package com.project1.dao;

import com.project1.models.Reimbursement;

import java.util.List;

public interface ReimbursementDao {
    public void insertReimbursement(Reimbursement newRequest);

    List<Reimbursement> getReimbursements();

    List<Reimbursement> getPendingReimbursements();

    List<Reimbursement> getMyPending(String username);
}
