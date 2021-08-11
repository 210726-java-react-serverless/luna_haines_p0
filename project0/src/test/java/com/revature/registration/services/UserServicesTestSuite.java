package com.revature.registration.services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.repositories.FacultyRepository;
import com.revature.registration.repositories.StudentRepository;
import com.revature.registration.util.Session;
import com.revature.registration.util.exceptions.AuthenticationException;
import com.revature.registration.util.exceptions.InvalidInformationException;
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

    @Test(expected = InvalidInformationException.class)
    public void isStudentValid_throwsInvalidInformationException_whenGivenEmailWithoutAt() {
        // Arrange
        Student invalidStudent = new Student();
        invalidStudent.setFirstName("valid");
        invalidStudent.setLastName("valid");
        invalidStudent.setEmail("invalid");
        invalidStudent.setPassword("validity");

        // Act & Assert
        sut.isStudentValid(invalidStudent);
    }

    @Test(expected = InvalidInformationException.class)
    public void isStudentValid_throwsInvalidInformationException_whenGivenShortPassword() {
        // Arrange
        Student invalidStudent = new Student();
        invalidStudent.setFirstName("valid");
        invalidStudent.setLastName("valid");
        invalidStudent.setEmail("valid@valid.com");
        invalidStudent.setPassword("v");

        // Act & Assert
        sut.isStudentValid(invalidStudent);
    }

    @Test(expected = InvalidInformationException.class)
    public void isStudentValid_throwsInformationInvalidException_whenGivenEmptyParameters()  {
        // Arrange
        Student invalidStudent = new Student();
        invalidStudent.setFirstName("");
        invalidStudent.setLastName("");
        invalidStudent.setEmail("");
        invalidStudent.setPassword("");

        // Act & Assert
        sut.isStudentValid(invalidStudent);
    }

    @Test(expected = InvalidInformationException.class)
    public void isStudentValid_throwsInformationInvalidException_whenGivenNullParameters()  {
        // Arrange
        Student invalidStudent = new Student();
        invalidStudent.setFirstName(null);
        invalidStudent.setLastName(null);
        invalidStudent.setEmail(null);
        invalidStudent.setPassword(null);

        // Act & Assert
        sut.isStudentValid(invalidStudent);
    }

    @Test
    public void isStudentValid_checksForExistingEmails_whenEmailAlreadyRegistered() {
        // Arrange
        Student invalidStudent = new Student();
        invalidStudent.setFirstName("testy");
        invalidStudent.setLastName("feisty");
        invalidStudent.setEmail("peter.parker@asgard.net");
        invalidStudent.setPassword("validpass");

        // Act
        boolean result = sut.isStudentValid(invalidStudent);

        // Assert
        verify(mockStudentRepo,times(1)).findByEmail(any());
    }

    @Test
    public void registerStudent_returnsSuccessfully_whenGivenValidUser() {
        // Arrange
        Student validStudent = new Student();
        validStudent.setFirstName("valid");
        validStudent.setLastName("valid");
        validStudent.setEmail("valid@valid.com");
        validStudent.setPassword("validity");

        // Act
        Student result = sut.registerStudent(validStudent);

        // Assert
        verify(mockStudentRepo,times(1)).save(any());
        Assert.assertEquals(validStudent.getFirstName(),result.getFirstName());
        Assert.assertEquals(validStudent.getLastName(),result.getLastName());
        Assert.assertEquals(validStudent.getEmail(),result.getEmail());
        Assert.assertEquals(validStudent.getPassword(),result.getPassword());
    }

    @Test
    public void loginStudent_returnsSuccessfully_whenGivenCorrectCredentials() {
        // Arrange
        String givenEmail = "test@test.com";
        String givenPassword = "password";
        Student returnedStudent = new Student();
        returnedStudent.setFirstName("valid");
        returnedStudent.setLastName("valid");
        returnedStudent.setEmail("test@test.com");
        returnedStudent.setPassword("password");
        when(mockStudentRepo.findByCredentials(givenEmail,givenPassword)).thenReturn(returnedStudent);

        // Act
        sut.loginStudent(givenEmail,givenPassword);

        // Assert
        verify(mockStudentRepo,times(1)).findByCredentials(givenEmail,givenPassword);
        Assert.assertEquals(returnedStudent.getEmail(),givenEmail);
        Assert.assertEquals(returnedStudent.getPassword(),givenPassword);
    }

    @Test(expected = AuthenticationException.class)
    public void loginStudent_throwsAuthenticationException_whenGivenInvalidCredentials() {
        // Arrange
        String givenEmail = "test@test.com";
        String givenPassword = "password";
        when(mockStudentRepo.findByCredentials(givenEmail,givenPassword)).thenReturn(null);

        // Act
        try {
            sut.loginStudent(givenEmail, givenPassword);
        } finally {
            // Assert
            verify(mockStudentRepo, times(1)).findByCredentials(givenEmail, givenPassword);
        }
    }
    @Test
    public void loginFaculty_returnsSuccessfully_whenGivenCorrectCredentials() {
        // Arrange
        String givenEmail = "test@test.com";
        String givenPassword = "password";
        Faculty returnedFaculty = new Faculty();
        returnedFaculty.setFirstName("valid");
        returnedFaculty.setLastName("valid");
        returnedFaculty.setEmail("test@test.com");
        returnedFaculty.setPassword("password");
        when(mockFacultyRepo.findByCredentials(givenEmail,givenPassword)).thenReturn(returnedFaculty);

        // Act
        sut.loginFaculty(givenEmail,givenPassword);

        // Assert
        verify(mockFacultyRepo,times(1)).findByCredentials(givenEmail,givenPassword);
        Assert.assertEquals(returnedFaculty.getEmail(),givenEmail);
        Assert.assertEquals(returnedFaculty.getPassword(),givenPassword);
    }

    @Test(expected = AuthenticationException.class)
    public void loginFaculty_throwsAuthenticationException_whenGivenInvalidCredentials() {
        // Arrange
        String givenEmail = "test@test.com";
        String givenPassword = "password";
        when(mockFacultyRepo.findByCredentials(givenEmail, givenPassword)).thenReturn(null);

        // Act
        try {
            sut.loginFaculty(givenEmail, givenPassword);
        } finally {
            // Assert
            verify(mockFacultyRepo, times(1)).findByCredentials(givenEmail, givenPassword);
        }
    }
}
