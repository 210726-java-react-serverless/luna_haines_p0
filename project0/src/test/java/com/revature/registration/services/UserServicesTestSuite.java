package com.revature.registration.services;

import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;
import com.revature.registration.util.Session;

public class UserServicesTestSuite {

    UserServices sut;

    private Session mockSession;
    private StudentRepository mockStudentRepo;
    private FacultyRepository mockFacultyRepo;
}
