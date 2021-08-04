package com.revature.registration.screens;

import com.revature.registration.models.User;
import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;
import java.sql.SQLOutput;

public class StudentRegistrationScreen extends Screen {

    public StudentRegistrationScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Student Registration Screen", "/studentregistration", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        System.out.println("Register a new student account:");

        System.out.println("First Name: ");
        String firstName = consoleReader.readLine();

        System.out.println("Last Name: ");
        String lastName = consoleReader.readLine();

        System.out.println("Email: ");
        String email = consoleReader.readLine();

        System.out.println("Password: ");
        String password = consoleReader.readLine();

        int id = 0; // TODO implement random id values (hashcode?)

        // TODO create student here, save their info to the database

    }

    // TODO store user info in a database

}
