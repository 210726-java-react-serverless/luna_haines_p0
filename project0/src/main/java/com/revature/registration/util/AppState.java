package com.revature.registration.util;

import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;
import com.revature.registration.screens.*;
import com.revature.registration.services.UserServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean appRunning;
    private ScreenRouter router;

    public AppState() {
        appRunning = true;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        StudentRepository studentRepository = new StudentRepository();
        FacultyRepository facultyRepository = new FacultyRepository();
        UserServices userServices = new UserServices(studentRepository,facultyRepository);
        Session user = Session.getInstance();

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader,router));
        router.addScreen(new LoginScreen(consoleReader,router,userServices));
        router.addScreen(new RegistrationScreen(consoleReader,router,userServices));
        router.addScreen(new StudentDashboard(consoleReader,router,userServices,user.getStudent()));
        router.addScreen(new FacultyDashboard(consoleReader,router,userServices,user.getFaculty()));

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

    public static void shutdown() {
        ConnectionFactory.getInstance().cleanUp();
        appRunning = false;
    }

}
