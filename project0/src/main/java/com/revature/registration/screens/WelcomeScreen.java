package com.revature.registration.screens;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(BufferedReader consoleReader) {
        super("Welcome Screen", "/welcome", consoleReader);
    }

    @Override
    public void render() throws Exception {
        System.out.println(
                "Course Management:\n" +
                "1) Login\n" +
                "2) Register New User\n" +
                "3) Exit Application");

        String userSelection = consoleReader.readLine();

        switch (userSelection) {

            // TODO implement a screen router
            case "1":
                System.out.println("Login goes here");
                break;
            case "2":
                System.out.println("User Registration goes here");
                break;
            case "3":
                System.out.println("Exiting Application");
                break;
                // TODO close application appropriately here
            default:
                System.out.println("Please enter a valid input");
        }

    }
}
