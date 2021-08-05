package com.revature.registration.models;

import java.util.Objects;

public class Faculty extends User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int _id;

    public Faculty(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
