package com.revature.registration.screens;

import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;

public class FacultyDashboard extends Screen {

    public FacultyDashboard(BufferedReader consoleReader, ScreenRouter router) {
        super("Faculty Dashboard", "/facultydashboard", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        System.out.println( "Faculty Dashboard\n" +
                            "1) Add a New Course\n" +
                            "2) Update Course Registration Details\n" +
                            "3) Remove a Course");
        String userChoice = consoleReader.readLine();

        switch (Integer.parseInt(userChoice)) {
            case 1:
                System.out.println("Add a Course");
            case 2:
                System.out.println("Update a Course");
            case 3:
                System.out.println("Remove a Course");
            default:
                System.out.println("Please enter a valid input");
        }
    }
}
