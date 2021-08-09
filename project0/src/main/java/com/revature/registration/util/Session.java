package com.revature.registration.util;

import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;

public class Session {
    private static Session user = null;
    private Student student;
    private Faculty faculty;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public static Session getInstance() {
        return user;
    }
}
