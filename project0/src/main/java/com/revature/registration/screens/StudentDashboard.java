package com.revature.registration.screens;

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
                            "4) Cancel Your Registration");
        String userChoice = consoleReader.readLine();

        switch (Integer.parseInt(userChoice)) {
            case 1:
                System.out.println("Open Courses:");
                System.out.println("Query for open courses here");
            case 2:
                // TODO figure out how to track student information from login.
                // maybe make a field in UserRepository? or pass it to this class?
                System.out.println("Enter Course ID: ");
                String courseRegId = consoleReader.readLine();
                System.out.println("Register this student for this course");
            case 3:
                System.out.println("Your Registered Courses:");
                // TODO display courses
                System.out.println("Press enter to continue:");
                consoleReader.readLine();
            case 4:
                System.out.println("Enter Course ID: ");
                String courseDelId = consoleReader.readLine();
                System.out.println("Unregister this student from this course");
            default:
                System.out.println("Please enter a valid input");
        }
    }
}
