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


    public Student registerStudent(Student student) {
        if (!isStudentValid(student)) {
            throw new InvalidInformationException("The information (i.e. first name, last name, email, or password)" +
                    " you provided is not valid.");
        }
        if (studentRepo.findById(student.getId()) != null) {
            throw new InvalidInformationException("That email is already registered with this application.");
        }

        studentRepo.save(student);
        return student;
    }

    public Student loginStudent(String email, String password) throws AuthenticationException {
        Student student = studentRepo.findByCredentials(email,password);
        if (student == null) {
            throw new AuthenticationException("Invalid Username/Password combo");
        }
        return student;
    }

    public Faculty loginFaculty(String email, String password) throws AuthenticationException {
        Faculty faculty = facultyRepo.findByCredentials(email,password);
        if (faculty == null) {
            throw new AuthenticationException("Invalid Username/Password combo");
        }
        return faculty;
    }

    public boolean isStudentValid(Student student) {
        if (!student.getEmail().contains("@")) {
            return false;
        }
        return true;
    }
}
