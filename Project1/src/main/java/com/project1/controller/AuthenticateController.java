package com.project1.controller;

import com.project1.models.User;
import com.project1.service.AuthenticateService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class AuthenticateController {
    AuthenticateService as = new AuthenticateService();

    public void authenticateFromJSON(Context ctx) {
        // Check to be sure no one is logged in
        if (ctx.sessionAttribute("username") == null) {
            User u = ctx.bodyAsClass(User.class); // Create user object from JSON body
            u = as.authenticate(u.getUsername(), u.getPassword());
            if (u != null) { // If authentication retrieved the user's info
                // Create session attributes for Javalin to store the user's username and role
                ctx.sessionAttribute("username", u.getUsername());
                ctx.sessionAttribute("role", u.getRole());
                ctx.result("Logged in as " + u.getFirstName() + " " + u.getLastName());
            } else { // ie Wrong password, user doesn't exist
                ctx.result("Invalid credentials");
                ctx.status(HttpCode.FORBIDDEN);
            }
        } else { // If someone is logged in
            ctx.result("User already logged in; please log out first");
            ctx.status(HttpCode.FORBIDDEN);
        }

    }

    public void logout(Context ctx) {
        // Consumes session attributes
        ctx.consumeSessionAttribute("username");
        ctx.consumeSessionAttribute("role");

    }

}
