package com.project1.controller;

import com.project1.models.Reimbursement;
import com.project1.service.ReimbursementService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import org.eclipse.jetty.client.HttpResponse;

import javax.xml.ws.spi.http.HttpHandler;
import java.util.List;

public class ReimbursementController {

    ReimbursementService reimbursementService = new ReimbursementService();
    public void createReimbursementRequest(Context ctx) {

        if (ctx.sessionAttribute("role") == null) {
            ctx.result("Not logged in; please log in before making a request");
            ctx.status(HttpCode.FORBIDDEN);
        } else if (ctx.sessionAttribute("role").toString().equals("employee")) {
            Reimbursement requestJSON = ctx.bodyAsClass(Reimbursement.class);
            requestJSON.setRequesterUsername(ctx.sessionAttribute("username"));
            boolean newRequestCreated = reimbursementService.createNewRequest(requestJSON);
            if (newRequestCreated) {
                ctx.status(HttpCode.CREATED);
            } else {
                ctx.status(HttpCode.BAD_REQUEST);
            }
        } else {
            ctx.result("Must be logged in as employee to request reimbursement");
            ctx.status(HttpCode.FORBIDDEN);
        }


    }

    public void getPendingReimbursements(Context ctx) {
        if (ctx.sessionAttribute("role") != null
                && ctx.sessionAttribute("role").toString().equals("manager")){
            List<Reimbursement> allPendingReimbursements = reimbursementService.getPendingReimbursements();
            ctx.json(allPendingReimbursements);
            ctx.status(HttpCode.OK);
        } else if (ctx.sessionAttribute("role") != null
                && ctx.sessionAttribute("role").toString().equals("employee")){
            List<Reimbursement> myPendingReimbursements = reimbursementService
                    .getPendingByUsername(ctx.sessionAttribute("username").toString());
            ctx.json(myPendingReimbursements);
            ctx.status(HttpCode.OK);
        } else {
            ctx.result("Not logged in; cannot view reimbursement requests");
            ctx.status(HttpCode.FORBIDDEN);
        }


    }

    public void getAllReimbursements(Context ctx) {
        if (ctx.sessionAttribute("role") != null) {
            List<Reimbursement> reimbursements;
            if (ctx.sessionAttribute("role").toString().equals("employee")) {
                // TODO - get all reimbursements for the employee
                String requestingEmployee = ctx.sessionAttribute("username").toString();
                reimbursements = reimbursementService.getReimbursements(requestingEmployee);
            } else {
                reimbursements = reimbursementService.getAllReimbursements();
                // TODO - get all reimbursements for everyone
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
                String requestingEmployee = ctx.sessionAttribute("username").toString();
                reimbursements = reimbursementService.getReviewedReimbursements(requestingEmployee);
                ctx.json(reimbursements);
            } else {
                reimbursements = reimbursementService.getReviewedReimbursements();
                ctx.json(reimbursements);
            }
        } else {
            ctx.result("Not logged in; cannot view reimbursement requests");
            ctx.status(HttpCode.BAD_REQUEST);
        }
    }

    public void getReimbursementsForUser(Context ctx) {
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
