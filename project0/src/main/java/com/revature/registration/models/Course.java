package com.revature.registration.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    @JsonSerialize(using = ToStringSerializer.class)
    public String id;
    public String number;
    public String name;
    public String description;
    public String professor;
    public int capacity;
    public String[] students;

    public Course() {}

    public Course(String number, String name, String description, String professor, int capacity, String... students) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.professor = professor;
        this.capacity = capacity;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "number='" + number + '\'' +
                "\n  name='" + name + '\'' +
                "\n  description='" + description + '\'' +
                "\n  professorEmail='" + professor + '\'' +
                "\n  capacity=" + capacity +
                "\n  studentEmails=" + Arrays.toString(students) +
                '}';
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String[] getStudents() {
        return students;
    }

    public void setStudents(String[] students) {
        this.students = students;
    }
}
