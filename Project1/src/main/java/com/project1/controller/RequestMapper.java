package com.project1.controller;

import com.project1.models.Employee;
import io.javalin.Javalin;

public class RequestMapper {

    private final EmployeeController employeeController = new EmployeeController();
    private final ManagerController managerController = new ManagerController();
    private final ReimbursementController reimbursementController = new ReimbursementController();
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
            Employee e = ctx.bodyAsClass(Employee.class);
            ctx.sessionAttribute("employee", e);
        });

        app.get("/api/session/secret", ctx -> {
            Employee e = ctx.sessionAttribute("employee");
            System.out.println(e.getUsername());
        });

        app.get("/api/session/end", ctx -> {
            ctx.consumeSessionAttribute("employee");
        });


    }
}
