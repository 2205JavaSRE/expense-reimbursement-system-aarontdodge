package com.project1.controller;

import io.javalin.Javalin;

public class RequestMapper {
    
    private final ReimbursementController reimbursementController = new ReimbursementController();
    private final AuthenticateController authenticateController = new AuthenticateController();
    public void configureRoutes(Javalin app) {

        app.post("/api/request", ctx -> {
            reimbursementController.createReimbursementRequest(ctx);
        });

        app.get("/api/requests", ctx -> {
            reimbursementController.getAllReimbursements(ctx);
        });

        app.get("/api/requests/user/{username}", ctx -> {
            reimbursementController.getReimbursementsForUser(ctx);
        });

        app.get("/api/requests/pending", ctx -> {
            reimbursementController.getPendingReimbursements(ctx);
        });

        app.get("/api/requests/reviewed", ctx -> {
            reimbursementController.getReviewedReimbursements(ctx);
        });

        app.patch("/api/request/{id}", ctx -> {
            reimbursementController.reviewRequest(ctx);
        });

        app.post("/api/session", ctx -> {
            authenticateController.authenticateFromJSON(ctx);
        });

        app.get("/api/session/end", ctx -> {
            authenticateController.logout(ctx);
        });


    }
}
