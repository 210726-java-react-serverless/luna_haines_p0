package com.revature.registration.services;

import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;
import com.revature.registration.util.exceptions.AuthenticationException;
import com.revature.registration.util.exceptions.InvalidInformationException;

public class UserServices {
    private StudentRepository studentRepo;
    private FacultyRepository facultyRepo;

    public UserServices(StudentRepository studentRepo, FacultyRepository facultyRepo) {
        this.studentRepo = studentRepo;
        this.facultyRepo = facultyRepo;
    }

    // TODO connect to database with repository classes
    public Student registerStudent(Student student) {
        if (!isStudentValid(student)) {
            throw new InvalidInformationException("The information (i.e. first name, last name, email, or password)" +
                    " you provided is not valid.");
        }
        if (studentRepo.findByEmail(student.getEmail()) != null) {
            throw new InvalidInformationException("That email is already registered with this application.");
        }

        // TODO persist user to db
        return student;
    }

    public Student loginStudent(String email, String password) throws AuthenticationException {
        // TODO if email/password combo not in database, then throw AuthenticationException
        Student student = studentRepo.findByEmail(email);
        return student;
    }

    public Faculty loginFaculty(String email, String password) throws AuthenticationException {
        // TODO if email/password combo not in database, then throw AuthenticationException
        Faculty faculty = facultyRepo.findByCredentials(email,password);
        return faculty;
    }

    public boolean isStudentValid(Student student) {
        if (!student.getEmail().contains("@")) {
            return false;
        }
        return true;
    }
}
