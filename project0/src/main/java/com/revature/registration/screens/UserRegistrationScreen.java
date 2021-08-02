package com.revature.registration.screens;

import com.revature.registration.models.User;

import java.io.BufferedReader;

public class UserRegistrationScreen extends Screen {

    public UserRegistrationScreen(BufferedReader consoleReader) {
        super("User Registration Screen", "/userregistration", consoleReader);
    }

    // TODO store user info in a database
}
