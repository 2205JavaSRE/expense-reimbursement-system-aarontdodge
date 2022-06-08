package com.project1.service;

import com.project1.dao.ReimbursementDao;
import com.project1.dao.ReimbursementDaoImpl;
import com.project1.models.Reimbursement;

import java.util.List;

public class ReimbursementService {

    private final ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
    public boolean createNewRequest(Reimbursement reimbursement) {
        // Reimbursement requests must be greater than 0
        if (reimbursement.getAmount() <= 0) {
            return false;
        } else {
            reimbursementDao.insertReimbursement(reimbursement);
            return true;
        }
    }

    public List<Reimbursement> getPendingReimbursements() {
        return reimbursementDao.getPendingReimbursements();
    }

    public List<Reimbursement> getPendingByUsername(String username) {
        return reimbursementDao.getMyPending(username);
    }


    public List<Reimbursement> getReimbursements(String username) {
        return reimbursementDao.getReimbursementsByUsername(username);
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDao.getReimbursements();
    }

    public List<Reimbursement> getReviewedReimbursements(String requestingEmployee) {
        return reimbursementDao.getReviewedReimbursements(requestingEmployee);
    }

    public List<Reimbursement> getReviewedReimbursements() {
        return reimbursementDao.getReviewedReimbursements();
    }

    public void updateRequest(Reimbursement updatedRequest) {
        reimbursementDao.updateRequest(updatedRequest.getId(), updatedRequest.isApproved());
    }
}
