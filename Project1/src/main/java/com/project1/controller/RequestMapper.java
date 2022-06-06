package com.project1.controller;

import io.javalin.Javalin;

public class RequestMapper {
    
    private final ReimbursementController reimbursementController = new ReimbursementController();
    private final AuthenticateController authenticateController = new AuthenticateController();
    public void configureRoutes(Javalin app) {

        app.post("/api/request", reimbursementController::createReimbursementRequest);

        app.get("/api/requests", reimbursementController::getAllReimbursements);

        app.get("/api/requests/user/{username}", reimbursementController::getReimbursementsForUser);

        app.get("/api/requests/pending", reimbursementController::getPendingReimbursements);

        app.get("/api/requests/reviewed", reimbursementController::getReviewedReimbursements);

        app.patch("/api/request/{id}", reimbursementController::reviewRequest);

        app.post("/api/session", authenticateController::authenticateFromJSON);

        app.get("/api/session/end", authenticateController::logout);


    }
}
