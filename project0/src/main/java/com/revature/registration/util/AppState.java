package com.revature.registration.util;

import com.revature.registration.screens.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private boolean appRunning;
    private ScreenRouter router;

    public AppState() {
        appRunning = true;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader,router));
        router.addScreen(new LoginScreen(consoleReader,router));
        router.addScreen(new RegistrationScreen(consoleReader,router));
        router.addScreen(new StudentDashboard(consoleReader,router));
        router.addScreen(new FacultyDashboard(consoleReader,router));

    }

    public void startup() {

        router.navigate("/welcome");

        while (appRunning) {
            try {
                router.getCurrentScreen().render();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
