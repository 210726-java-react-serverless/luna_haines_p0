package com.revature.registration.screens;

import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.services.UserServices;
import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    // TODO consider replacing this with a UserSession singleton class
    public static Student student;
    public static Faculty faculty;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Login Screen", "/login", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        System.out.println( "Login Screen:\n" +
                            "1) Faculty\n" +
                            "2) Student");
        System.out.print("> ");
        int userType = Integer.parseInt(consoleReader.readLine());

        System.out.println("Email: ");
        String email = consoleReader.readLine();

        System.out.println("Password: ");
        String password = consoleReader.readLine();

        // TODO authenticate users via database queries
        switch (userType) {
            case 1:
                faculty = UserServices.loginFaculty(email,password);
                router.navigate("/facultydashboard");
                break;
            case 2:
                student = UserServices.loginStudent(email,password);
                router.navigate("/studentdashboard");
                break;
            default:
                System.out.println("Please enter a valid input");
        }
    }

}
