package com.project1.controller;

import com.project1.models.Reimbursement;
import com.project1.service.ReimbursementService;
import io.javalin.http.Context;

import java.util.List;

public class ReimbursementController {

    ReimbursementService reimbursementService = new ReimbursementService();
    public void createReimbursementRequest(Context cxt) {
        Reimbursement requestJSON = cxt.bodyAsClass(Reimbursement.class);
        boolean newRequestCreated = reimbursementService.createNewRequest(requestJSON);
        if (newRequestCreated) {
            System.out.println("New request created!");
        } else {
            System.out.println("Invalid request");
        }
    }

    public void getPendingReimbursements(Context cxt) {
        List<Reimbursement> pendingReimbursements = reimbursementService.getPendingReimbursements();
        cxt.json(pendingReimbursements);
    }

    public void getAllReimbursements(Context cxt) {
    }
}
