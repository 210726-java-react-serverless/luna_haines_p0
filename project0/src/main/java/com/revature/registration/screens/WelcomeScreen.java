package com.revature.registration.screens;

public class WelcomeScreen extends Screen {

    public WelcomeScreen() {
        super("Welcome Screen", "/welcome");
    }

    public void render() {
        System.out.println(
                "Course Management:\n" +
                "1) Login\n" +
                "2) Register New User\n" +
                "3) Exit Application");
        
    }
}
