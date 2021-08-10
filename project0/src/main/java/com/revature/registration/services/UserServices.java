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
        isStudentValid(student);
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

    public boolean isStudentValid(Student student) throws InvalidInformationException{
        System.out.println(student.toString());
        if (!student.getEmail().contains("@")) {
            throw new InvalidInformationException("Email provided was not a valid email");
        }
        if (student.getPassword().length()<4) {
            throw new InvalidInformationException("Password provided was not long enough");
        }
        if (student.getFirstName() == null || student.getLastName() == null || student.getEmail() == null ||
                student.getPassword() == null || student.getFirstName().equals("") ||
                student.getLastName().equals("") || student.getEmail().equals("") || student.getPassword().equals("")) {

            throw new InvalidInformationException("No field can be left blank");
        }
        if (studentRepo.findByEmail(student.getEmail()) != null) {
            throw new InvalidInformationException("That email is already registered with this application.");
        }

        return true;
    }
}
