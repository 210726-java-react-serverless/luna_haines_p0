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

    public Course createCourse(Course newCourse) {
        return courseRepo.save(newCourse);
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

    public void registerForCourse(String number, Student student) {
        courseRepo.addStudent(number,student.getEmail());
    }

    public  void removeFromCourse(String number, Student student) {
        courseRepo.removeStudent(number,student.getEmail());
    }

    public boolean updateCourse(String currentNumber,String field, String newValue) {
        return courseRepo.update(currentNumber,field,newValue);
    }

    public boolean removeCourse(String number) {
        return courseRepo.deleteByNumber(number);
    }

}
