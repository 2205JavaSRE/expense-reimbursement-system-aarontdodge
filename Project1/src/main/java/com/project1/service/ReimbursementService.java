package com.project1.service;

import com.project1.dao.ReimbursementDao;
import com.project1.models.Reimbursement;

public class ReimbursementService {

    private final ReimbursementDao reimbursementDao = new ReimbursementDao();
    public boolean createNewRequest(Reimbursement reimbursement) {
        if (reimbursement.getAmount() <= 0) {
            return false;
        } else {
            Reimbursement newRequest = new Reimbursement(); // TODO - add necessary constructor fields
            reimbursementDao.insertReimbursement(newRequest);
            return true;
        }
    }
}
