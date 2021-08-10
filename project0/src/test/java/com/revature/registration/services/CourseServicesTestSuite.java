package com.revature.registration.services;

import com.revature.registration.repositories.CourseRepository;
import com.revature.registration.util.Session;
import org.junit.*;
import static org.mockito.Mockito.*;

public class CourseServicesTestSuite {
    CourseServices sut;

    private Session mockSession;
    private CourseRepository mockCourseRepo;

    @Before
    public void beforeTests() {
        mockSession = mock(Session.class);
        mockCourseRepo = mock(CourseRepository.class);
        sut = new CourseServices(mockCourseRepo);
    }

    @After
    public void afterTests() {
        sut = null;
    }
}
