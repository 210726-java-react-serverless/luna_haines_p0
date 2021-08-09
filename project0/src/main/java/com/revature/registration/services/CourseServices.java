package com.revature.registration.services;

import com.revature.registration.models.Course;
import com.revature.registration.models.Student;
import com.revature.registration.repositories.CourseRepository;

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

    public void registerForCourse(Student student) {

    }

    private boolean isCourseOpen() {
        // TODO check course capacity against # of students registered
        return false;
    }

}
