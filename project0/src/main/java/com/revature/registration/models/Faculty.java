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

    @Override
    public String toString() {
        return "Faculty{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + _id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return _id == faculty._id && Objects.equals(firstName, faculty.firstName) && Objects.equals(lastName, faculty.lastName) && Objects.equals(email, faculty.email) && Objects.equals(password, faculty.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, _id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }
}
