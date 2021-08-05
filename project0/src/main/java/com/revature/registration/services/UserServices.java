package com.revature.registration.services;

import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;

public class UserServices {
    private StudentRepository studentRepo;
    private FacultyRepository facultyRepo;

    public UserServices(StudentRepository studentRepo, FacultyRepository facultyRepo) {
        this.studentRepo = studentRepo;
        this.facultyRepo = facultyRepo;
    }

    // TODO connect to database with repository classes
    public Student registerStudent(Student student) {
        return null;
    }

    public Faculty registerFaculty(Faculty faculty) {
        return null;
    }

    public Student loginStudent(Student student) {
        return null;
    }

    public Faculty loginFaculty(Faculty faculty) {
        return null;
    }
}
