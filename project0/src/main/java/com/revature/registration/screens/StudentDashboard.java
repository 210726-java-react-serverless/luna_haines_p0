package com.revature.registration.screens;

import com.revature.registration.models.Course;
import com.revature.registration.models.Student;
import com.revature.registration.services.CourseServices;
import com.revature.registration.services.UserServices;
import com.revature.registration.util.AppState;
import com.revature.registration.util.ScreenRouter;
import com.revature.registration.util.Session;

import java.io.BufferedReader;

public class StudentDashboard extends Screen {

    private final UserServices userServices;
    private final CourseServices courseServices;
    public Student student;

    public StudentDashboard(BufferedReader consoleReader, ScreenRouter router, UserServices userServices,
                            CourseServices courseServices) {
        super("Student Dashboard", "/studentdashboard", consoleReader, router);
        this.userServices = userServices;
        this.courseServices = courseServices;
    }

    @Override
    public void render() throws Exception {
        Student student = Session.getInstance().getStudent();
        System.out.println( "Student Dashboard\n" +
                            "1) View Course List\n" +
                            "2) Register for a Course\n" +
                            "3) View Your Registered Courses\n" +
                            "4) Cancel Your Registration\n" +
                            "5) View User Info\n" +
                            "6) Exit Application");
        System.out.print("> ");
        String userChoice = consoleReader.readLine();


        switch (Integer.parseInt(userChoice)) {
            case 1:
                System.out.println("Course List:");
                for (Course c : courseServices.getCourseList()) {
                    System.out.println(c.toString());
                }
                break;
            case 2:
                System.out.println("Enter Course Number: ");
                String courseRegNumber = consoleReader.readLine();
                courseServices.registerForCourse(courseRegNumber,student);
                break;
            case 3:
                System.out.println("Your Registered Courses:");
                for ( Course c : courseServices.getRegisteredCourses(student)) {
                    System.out.println(c.toString());
                }
                break;
            case 4:
                System.out.println("Enter Course Number:");
                String courseDelNumber = consoleReader.readLine();
                courseServices.removeFromCourse(courseDelNumber,student);
                break;
            case 5:
                System.out.println("User Info:");
                System.out.println(student.getFirstName());
                System.out.println(student.getLastName());
                System.out.println(student.getEmail());
                break;
            case 6:
                System.out.println("Exiting Application...");
                AppState.shutdown();
                break;
            default:
                System.out.println("Please enter a valid input");
        }
    }
}
