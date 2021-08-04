package com.revature.registration.screens;

import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Login Screen", "/login", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        System.out.println( "Login Screen:\n" +
                            "1) Faculty\n" +
                            "2) Student");
        int userType = Integer.parseInt(consoleReader.readLine());

        System.out.println("Email: ");
        String email = consoleReader.readLine();

        System.out.println("Password: ");
        String password = consoleReader.readLine();

        // TODO authenticate users via database queries
        switch (userType) {
            case 1:
                // Authentication here
                router.navigate("/facultydashboard");
            case 2:
                //Authentication here
                router.navigate("/studentdashboard");
            default:
                System.out.println("Please enter a valid input");
        }
    }

}
