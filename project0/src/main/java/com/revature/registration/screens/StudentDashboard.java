package com.revature.registration.screens;

import com.revature.registration.util.AppState;
import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;

public class StudentDashboard extends Screen {

    public StudentDashboard(BufferedReader consoleReader, ScreenRouter router) {
        super("Student Dashboard", "student", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        System.out.println( "Student Dashboard\n" +
                            "1) View List of Available Courses\n" +
                            "2) Register for a Course\n" +
                            "3) View Your Registered Courses\n" +
                            "4) Cancel Your Registration\n" +
                            "5) View User Info\n" +
                            "6) Exit Application");
        System.out.print("> ");
        String userChoice = consoleReader.readLine();

        switch (Integer.parseInt(userChoice)) {
            case 1:
                System.out.println("Open Courses:");
                System.out.println("Query for open courses here");
                break;
            case 2:
                // TODO figure out how to track student information from login.
                // maybe make a field in UserRepository? or pass it to this class?
                System.out.println("Enter Course ID: ");
                String courseRegId = consoleReader.readLine();
                System.out.println("Register this student for this course");
                break;
            case 3:
                System.out.println("Your Registered Courses:");
                // TODO display courses
                System.out.println("Press enter to continue:");
                consoleReader.readLine();
                break;
            case 4:
                System.out.println("Enter Course ID:");
                String courseDelId = consoleReader.readLine();
                System.out.println("Unregister this student from this course");
                break;
            case 5:
                System.out.println("User Info:");
                LoginScreen.student.getFirstName();
                LoginScreen.student.getLastName();
                LoginScreen.student.getEmail();
                break;
            case 6:
                System.out.println("Exiting Application...");
                AppState.shutdown();
                break;
            default:
                System.out.println("Please enter a valid input");
        }
    }
}
