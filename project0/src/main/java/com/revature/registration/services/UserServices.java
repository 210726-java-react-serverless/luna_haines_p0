package com.revature.registration.services;

import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;
import com.revature.registration.util.exceptions.AuthenticationException;

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

    public static Student loginStudent(String email, String password) {
        // // TODO if email/password combo not in database, then throw AuthenticationException
        return null;
    }

    public static Faculty loginFaculty(String email, String password) throws AuthenticationException {
        // TODO if email/password combo not in database, then throw AuthenticationException
        return null;
    }

    public static boolean isStudentValid(String firstName, String lastName, String email, String password) {
        if (!email.contains("@")) {
            return false;
        }
        // TODO check if email is already in db
        return true;
    }
}
