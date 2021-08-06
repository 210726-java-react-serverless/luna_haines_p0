package com.revature.registration.models;

import java.util.Objects;

public class Student extends User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String id;

    public Student(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
