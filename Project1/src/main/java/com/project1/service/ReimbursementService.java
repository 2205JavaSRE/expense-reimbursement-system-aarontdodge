package com.project1.service;

import com.project1.dao.ReimbursementDao;
import com.project1.dao.ReimbursementDaoImpl;
import com.project1.models.Reimbursement;

import java.util.List;

public class ReimbursementService {

    private final ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
    public boolean createNewRequest(Reimbursement reimbursement) {
        if (reimbursement.getAmount() <= 0) {
            return false;
        } else {
            reimbursementDao.insertReimbursement(reimbursement);
            return true;
        }
    }

    public List<Reimbursement> getPendingReimbursements() {
        List<Reimbursement> reimbursements = reimbursementDao.getPendingReimbursements();
        return reimbursements;
    }
}
