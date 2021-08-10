package com.revature.registration.screens;

import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.services.UserServices;
import com.revature.registration.util.ScreenRouter;
import com.revature.registration.util.Session;
import com.revature.registration.util.exceptions.AuthenticationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;

public class LoginScreen extends Screen {

    private final Logger logger = LogManager.getLogger(LoginScreen.class);
    private final UserServices userServices;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserServices userServices) {
        super("Login Screen", "/login", consoleReader, router);
        this.userServices = userServices;
    }

    @Override
    public void render() throws Exception {
        System.out.println("----------------------------");
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
                    Session.getInstance().setFaculty(faculty);
                    logger.info("Successfully logged in");
                    router.navigate("/facultydashboard");
                    break;
                case 2:
                    Student student = userServices.loginStudent(email, password);
                    Session.getInstance().setStudent(student);
                    logger.info("Successfully logged in");
                    router.navigate("/studentdashboard");
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }
        } catch (AuthenticationException ae) {
            logger.error(ae.getMessage());
            logger.debug("User could not be authenticated");
            router.navigate("/welcome");
        }
    }
}
