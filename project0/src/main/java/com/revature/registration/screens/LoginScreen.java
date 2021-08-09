package com.revature.registration.screens;

import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.services.UserServices;
import com.revature.registration.util.ScreenRouter;
import com.revature.registration.util.exceptions.AuthenticationException;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private final UserServices userServices;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserServices userServices) {
        super("Login Screen", "/login", consoleReader, router);
        this.userServices = userServices;
    }

    @Override
    public void render() throws Exception {
        System.out.println("Login Screen:\n" +
                "1) Faculty\n" +
                "2) Student");
        System.out.print("> ");
        int userType = Integer.parseInt(consoleReader.readLine());

        System.out.println("Email: ");
        String email = consoleReader.readLine();

        System.out.println("Password: ");
        String password = consoleReader.readLine();
        try {
            switch (userType) {
                case 1:
                    Faculty faculty = userServices.loginFaculty(email, password);
                    router.navigate("/facultydashboard");
                    break;
                case 2:
                    Student student = userServices.loginStudent(email, password);
                    router.navigate("/studentdashboard");
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }
        } catch (AuthenticationException ae) {
            System.out.println("Could not find user with provided credentials.");
            System.out.println("Sending you back to Welcome...");
            router.navigate("/welcome");
        }
    }
}
