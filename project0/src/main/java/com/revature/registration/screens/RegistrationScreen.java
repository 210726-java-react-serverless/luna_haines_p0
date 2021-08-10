package com.revature.registration.screens;

import com.revature.registration.models.Student;
import com.revature.registration.models.User;
import com.revature.registration.services.UserServices;
import com.revature.registration.util.ScreenRouter;
import com.revature.registration.util.exceptions.InvalidInformationException;

import java.io.BufferedReader;
import java.sql.SQLOutput;

public class RegistrationScreen extends Screen {
    private final UserServices userServices;

    public RegistrationScreen(BufferedReader consoleReader, ScreenRouter router, UserServices userServices) {
        super("Registration Screen", "/registration", consoleReader, router);
        this.userServices = userServices;
    }

    @Override
    public void render() throws Exception {
        System.out.println("----------------------------");
        System.out.println("Register a new student account:");

        System.out.println("First Name: ");
        String firstName = consoleReader.readLine();

        System.out.println("Last Name: ");
        String lastName = consoleReader.readLine();

        System.out.println("Email: ");
        String email = consoleReader.readLine();

        System.out.println("Password: ");
        String password = consoleReader.readLine();

        Student newStudent = new Student(firstName,lastName,email,password);
        try {
            userServices.registerStudent(newStudent);
            router.navigate("/welcome");
        } catch (InvalidInformationException iie) {
            System.out.println(iie.getMessage());
        }
    }
}
