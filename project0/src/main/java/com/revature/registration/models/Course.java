package com.revature.registration.models;

public class Course {
    public String id;
    public String number;
    public String name;
    public String description;
    public String professorEmail;
    public int capacity;
    public String[] studentEmails;

    Course(String number, String name, String description, String professorEmail, int capacity, String... studentEmails) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.professorEmail = professorEmail;
        this.capacity = capacity;
        this.studentEmails = studentEmails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public String[] getStudentEmails() {
        return studentEmails;
    }

    public void setStudentEmails(String[] studentEmails) {
        this.studentEmails = studentEmails;
    }
}
