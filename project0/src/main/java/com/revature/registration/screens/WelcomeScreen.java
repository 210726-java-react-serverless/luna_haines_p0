package com.revature.registration.screens;

import com.revature.registration.App;
import com.revature.registration.util.AppState;
import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Welcome Screen", "/welcome", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        System.out.println("----------------------------");
        System.out.println(
                "Course Management:\n" +
                "1) Login\n" +
                "2) Register New User\n" +
                "3) Exit Application");
        System.out.print("> ");

        String userSelection = consoleReader.readLine();

        switch (userSelection) {


            case "1":
                router.navigate("/login");
                break;

            case "2":
                router.navigate("/registration");
                break;
            case "3":
                System.out.println("Exiting Application...");
                AppState.shutdown();
                break;
            default:
                System.out.println("Please enter a valid input");
        }

    }
}
