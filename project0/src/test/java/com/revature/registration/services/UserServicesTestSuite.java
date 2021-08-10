package com.revature.registration.services;

import com.revature.registration.models.Student;
import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;
import com.revature.registration.util.Session;
import org.junit.*;
import static org.mockito.Mockito.*;

public class UserServicesTestSuite {

    UserServices sut;

    private Session mockSession;
    private StudentRepository mockStudentRepo;
    private FacultyRepository mockFacultyRepo;

    @Before
    public void beforeTests() {
        mockSession = mock(Session.class);
        mockStudentRepo = mock(StudentRepository.class);
        mockFacultyRepo = mock(FacultyRepository.class);
        sut = new UserServices(mockStudentRepo,mockFacultyRepo);
    }

    @After
    public void afterTests() {
        sut = null;
    }

    @Test
    public void isStudentValid_returnsTrue_whenGivenValidStudent() {

        // Arrange
        Student validStudent = new Student();
        validStudent.setFirstName("valid");
        validStudent.setLastName("valid");
        validStudent.setEmail("valid@valid.com");
        validStudent.setPassword("validity");

        // Act
        boolean result = sut.isStudentValid(validStudent);

        // Assert
        Assert.assertTrue(result);
    }

    @Test(expected = )

}
