package com.project1.controller;

import com.project1.models.User;
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

        app.get("/api/requests/pending", ctx -> {
            reimbursementController.getPendingReimbursements(ctx);
        });

        app.get("/api/requests/past", ctx -> {

        });

        app.put("/api/request/{id}", ctx -> {});

        // TODO - figure out authentication
        app.post("/api/session", ctx -> {
            authenticateController.authenticateFromJSON(ctx);
        });

        app.get("/api/session/end", ctx -> {
            authenticateController.logout(ctx);
        });


    }
}
