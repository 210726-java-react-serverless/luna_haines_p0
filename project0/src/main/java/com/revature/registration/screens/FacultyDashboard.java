package com.revature.registration.screens;

import com.revature.registration.models.Course;
import com.revature.registration.models.Faculty;
import com.revature.registration.services.CourseServices;
import com.revature.registration.services.UserServices;
import com.revature.registration.util.AppState;
import com.revature.registration.util.ScreenRouter;

import java.io.BufferedReader;

public class FacultyDashboard extends Screen {

    private final UserServices userServices;
    private final CourseServices courseServices;
    public Faculty faculty;

    public FacultyDashboard(BufferedReader consoleReader, ScreenRouter router, UserServices userServices,
                            CourseServices courseServices, Faculty faculty) {
        super("Faculty Dashboard", "/facultydashboard", consoleReader, router);
        this.userServices = userServices;
        this.courseServices = courseServices;
        this.faculty = faculty;
    }

    @Override
    public void render() throws Exception {
        System.out.println( "Faculty Dashboard\n" +
                            "1) Add a New Course\n" +
                            "2) Update Course Registration Details\n" +
                            "3) Remove a Course\n" +
                            "4) View User Info\n" +
                            "5) Exit Application");
        System.out.print("> ");
        String userChoice = consoleReader.readLine();

        // TODO add methods from UserService to perform CRUD operations
        switch (Integer.parseInt(userChoice)) {
            case 1:
                System.out.println("Add a Course");
                System.out.println("Enter Course Number");
                System.out.print("> ");
                String number = consoleReader.readLine();

                System.out.println("Enter Course Name");
                System.out.print("> ");
                String name = consoleReader.readLine();

                System.out.println("Enter Course Description");
                System.out.print("> ");
                String description = consoleReader.readLine();

                String professorEmail = faculty.getEmail();

                System.out.println("Enter Course Capacity");
                System.out.print("> ");
                int capacity = Integer.parseInt(consoleReader.readLine());

                Course newCourse = new Course(number,name,description,professorEmail,capacity);

                break;
            case 2:
                System.out.println("Update a Course");
                break;
            case 3:
                System.out.println("Remove a Course");
                break;
            case 4:
                System.out.println("User Info:");
                faculty.getFirstName();
                faculty.getLastName();
                faculty.getEmail();
                System.out.println("Courses Taught:");
                System.out.println(courseServices.getTaughtCourses(faculty));
                break;
            case 5:
                System.out.println("Exiting Application");
                AppState.shutdown();
            default:
                System.out.println("Please enter a valid input");
        }
    }
}
