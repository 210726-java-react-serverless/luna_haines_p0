package com.revature.registration.screens;

import com.revature.registration.models.Student;
import com.revature.registration.models.User;
import com.revature.registration.services.UserServices;
import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;
import java.sql.SQLOutput;

public class RegistrationScreen extends Screen {

    public RegistrationScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Registration Screen", "/registration", consoleReader, router);
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

        if (!UserServices.isStudentValid(firstName,lastName,email,password)) {
            System.out.println("That information is invalid. Please ensure you entered a valid email address that" +
                    " isn't already registered.");
            return;
        }

        // TODO create student here, persist their info to the database

        Student newStudent = new Student(firstName,lastName,email,password);

        router.navigate("/welcome");
    }
}
