package com.project1.controller;

import com.project1.models.Reimbursement;
import com.project1.service.ReimbursementService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import java.util.List;

public class ReimbursementController {

    private final ReimbursementService reimbursementService = new ReimbursementService();
    public void createReimbursementRequest(Context ctx) {
        if (ctx.sessionAttribute("role") == null) {
            // If no user is logged in, return a FORBIDDEN Http code
            ctx.result("Not logged in; please log in before making a request");
            ctx.status(HttpCode.FORBIDDEN);
        } else if (ctx.sessionAttribute("role").toString().equals("employee")) {
            // If the logged-in user is an employee, they are allowed to create a new reimbursement request
            Reimbursement requestJSON = ctx.bodyAsClass(Reimbursement.class);
            requestJSON.setRequesterUsername(ctx.sessionAttribute("username"));
            boolean newRequestCreated = reimbursementService.createNewRequest(requestJSON);
            if (newRequestCreated) {
                ctx.status(HttpCode.CREATED);
            } else {
                ctx.status(HttpCode.BAD_REQUEST);
            }
        } else {
            // Other users (finance managers) are not allowed to request reimbursement
            ctx.result("Must be logged in as employee to request reimbursement");
            ctx.status(HttpCode.FORBIDDEN);
        }
    }

    public void getPendingReimbursements(Context ctx) {
        if (ctx.sessionAttribute("role") != null) {
            if (ctx.sessionAttribute("role").toString().equals("manager")) {
                // Finance managers can see requests made by all employees
                List<Reimbursement> allPendingReimbursements = reimbursementService.getPendingReimbursements();
                ctx.json(allPendingReimbursements);
                ctx.status(HttpCode.OK);
            } else {
                // Employees can only see their own reimbursement requests
                List<Reimbursement> myPendingReimbursements = reimbursementService
                        .getPendingByUsername(ctx.sessionAttribute("username").toString());
                ctx.json(myPendingReimbursements);
                ctx.status(HttpCode.OK);
            }
        } else {
            // A user must be logged in to view reimbursement requests
            ctx.result("Not logged in; cannot view reimbursement requests");
            ctx.status(HttpCode.FORBIDDEN);
        }
    }

    public void getAllReimbursements(Context ctx) {
        if (ctx.sessionAttribute("role") != null) {
            List<Reimbursement> reimbursements;
            if (ctx.sessionAttribute("role").toString().equals("employee")) {
                // Employees can only see their own reimbursement requests
                String requestingEmployee = ctx.sessionAttribute("username").toString();
                reimbursements = reimbursementService.getReimbursements(requestingEmployee);
            } else {
                // Finance managers can see requests made by all employees
                reimbursements = reimbursementService.getAllReimbursements();
            }
            ctx.json(reimbursements);
        } else {
            ctx.result("Not logged in; cannot view reimbursement requests");
            ctx.status(HttpCode.BAD_REQUEST);
        }
    }

    public void getReviewedReimbursements(Context ctx) {
        if (ctx.sessionAttribute("role") != null) {
            List<Reimbursement> reimbursements;
            if (ctx.sessionAttribute("role").toString().equals("employee")) {
                // Employees can only see their own reimbursement requests
                String requestingEmployee = ctx.sessionAttribute("username").toString();
                reimbursements = reimbursementService.getReviewedReimbursements(requestingEmployee);
                ctx.json(reimbursements);
            } else {
                // Finance managers can see requests made by all employees
                reimbursements = reimbursementService.getReviewedReimbursements();
                ctx.json(reimbursements);
            }
        } else {
            // A user must be logged in to view reimbursement requests
            ctx.result("Not logged in; cannot view reimbursement requests");
            ctx.status(HttpCode.BAD_REQUEST);
        }
    }

    public void getReimbursementsForUser(Context ctx) {
        // Only a finance manager can view reimbursements for a specified user by id
        if (ctx.sessionAttribute("role") != null) {
            if (ctx.sessionAttribute("role").toString().equals("employee")) {
                ctx.result("Forbidden: can only view your own reimbursement requests.");
                ctx.status(HttpCode.FORBIDDEN);
            } else {
                List<Reimbursement> reimbursements = reimbursementService.getReimbursements(ctx.pathParam("username"));
                ctx.json(reimbursements);
            }
        } else {
            ctx.result("Not logged in; cannot view reimbursement requests");
            ctx.status(HttpCode.BAD_REQUEST);
        }
    }

    public void reviewRequest(Context ctx) {
        // Only finance managers can review requests to approve or deny them
        if (ctx.sessionAttribute("role") != null) {
            if (ctx.sessionAttribute("role").toString().equals("employee")) {
                ctx.result("Forbidden: only finance managers can review requests.");
                ctx.status(HttpCode.FORBIDDEN);
            } else {
                Reimbursement updatedRequest = ctx.bodyAsClass(Reimbursement.class);
                updatedRequest.setId(Integer.parseInt(ctx.pathParam("id")));
                reimbursementService.updateRequest(updatedRequest);
            }
        } else {
            ctx.status(HttpCode.FORBIDDEN);
        }
    }
}
