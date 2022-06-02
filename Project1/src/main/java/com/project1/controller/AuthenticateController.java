package com.project1.controller;

import com.project1.models.Credentials;
import com.project1.models.User;
import com.project1.service.AuthenticateService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class AuthenticateController {
    AuthenticateService as = new AuthenticateService();

    public void authenticateFromJSON(Context ctx) {
        Credentials c = ctx.bodyAsClass(Credentials.class);
        User u = as.authenticate(c.getUsername(), c.getPassword());
        if (u != null) {
            ctx.sessionAttribute("username", u.getUsername());
            ctx.sessionAttribute("role", u.getRole());
            ctx.result("Logged in as " + u.getFirstName() + " " + u.getLastName());
        } else {
            ctx.result("Invalid credentials");
            ctx.status(HttpCode.FORBIDDEN);
        }
    }

    public void logout(Context ctx) {
        ctx.consumeSessionAttribute("username");
        ctx.consumeSessionAttribute("role");
    }

}
