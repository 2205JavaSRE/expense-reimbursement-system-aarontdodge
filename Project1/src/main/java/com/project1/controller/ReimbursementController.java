package com.project1.controller;

import com.project1.models.Reimbursement;
import com.project1.service.ReimbursementService;
import io.javalin.http.Context;

public class ReimbursementController {

    ReimbursementService reimbursementService = new ReimbursementService();
    public void createReimbursementRequest(Context cxt) {
        Reimbursement requestJSON = cxt.bodyAsClass(Reimbursement.class);
        reimbursementService.createNewRequest(requestJSON);
    }
}
