package com.revature.registration.util;

import com.revature.registration.repositories.CourseRepository;
import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;
import com.revature.registration.screens.*;
import com.revature.registration.services.CourseServices;
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
        CourseRepository courseRepository = new CourseRepository();
        UserServices userServices = new UserServices(studentRepository,facultyRepository);
        CourseServices courseServices = new CourseServices(courseRepository);
        Session user = Session.getInstance();

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader,router));
        router.addScreen(new LoginScreen(consoleReader,router,userServices));
        router.addScreen(new RegistrationScreen(consoleReader,router,userServices));
        router.addScreen(new StudentDashboard(consoleReader,router,userServices,courseServices));
        router.addScreen(new FacultyDashboard(consoleReader,router,userServices,courseServices));

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
