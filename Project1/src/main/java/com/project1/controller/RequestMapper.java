package com.project1.controller;

import io.javalin.Javalin;

public class RequestMapper {

    private final EmployeeController employeeController = new EmployeeController();
    private final ManagerController managerController = new ManagerController();
    private final ReimbursementController reimbursementController = new ReimbursementController();
    public void configureRoutes(Javalin app) {

        app.post("/api/request", cxt -> {
            reimbursementController.createReimbursementRequest(cxt);
        });

        app.get("/api/requests", cxt -> {

        });

        app.put("/api/request/{id}", cxt -> {});


    }
}
