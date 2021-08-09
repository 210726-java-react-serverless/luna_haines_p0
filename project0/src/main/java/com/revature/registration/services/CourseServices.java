package com.revature.registration.services;

import com.revature.registration.models.Course;
import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.repositories.CourseRepository;
import com.revature.registration.util.Session;

import java.util.List;

public class CourseServices {

    private CourseRepository courseRepo;

    public CourseServices(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> getCourseList() {
        return courseRepo.findAll();
    }

    public List<Course> getRegisteredCourses(Student student) {
        return courseRepo.findByStudent(student);
    }

    public List<Course> getTaughtCourses(Faculty faculty) {
        return courseRepo.findByFaculty(faculty);
    }

    public void registerForCourse(Student student,String number) {
        // TODO write this method, probably need a new update method in CourseRepository
    }

    public  void removeFromCourse(Student student,String number) {
        // TODO write this method, probably need a new update method in CourseRepository
    }

    private boolean isCourseOpen() {
        // TODO check course capacity against # of students registered
        return false;
    }

}
