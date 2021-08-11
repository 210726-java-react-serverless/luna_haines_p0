package com.revature.registration.services;

import com.revature.registration.models.Course;
import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.repositories.CourseRepository;
import com.revature.registration.util.Session;
import com.revature.registration.util.exceptions.DataSourceException;
import com.revature.registration.util.exceptions.InvalidInformationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseServices {

    private final Logger logger = LogManager.getLogger(CourseServices.class);
    private CourseRepository courseRepo;

    public CourseServices(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course createCourse(Course newCourse) {
        isCourseValid(newCourse);
        return courseRepo.save(newCourse);
    }

    public List<Course> getCourseList() {
        try {
            return courseRepo.findAll();
        } catch (DataSourceException dse) {
            logger.error(dse.getMessage());
        }
        return null;
    }

    public List<Course> getRegisteredCourses(Student student) {
        try {
            return courseRepo.findByStudent(student);
        } catch (DataSourceException dse) {
            logger.error(dse.getMessage());
        }
        return null;
    }

    public List<Course> getTaughtCourses(Faculty faculty) {
        try {
            return courseRepo.findByFaculty(faculty);
        } catch (DataSourceException dse) {
            logger.error(dse.getMessage());
        }
        return null;
    }

    public void registerForCourse(String number, Student student) {
        try {
            courseRepo.addStudent(number, student.getEmail());
        } catch (Exception e) {
            logger.error("A problem occurred while trying to add student to course list, " +
                    "check that you aren't already registered");
            throw new DataSourceException(e.getMessage(),e);
        }
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

    public boolean isCourseValid (Course course) throws InvalidInformationException {
        if (course.getCapacity() < 1) {
            throw new InvalidInformationException("Course capacity must be at least 1");
        }
        if (course.getDescription().length() > 280) {
            throw new InvalidInformationException("Course description cannot be more than 279 characters");
        }
        if (course.getNumber() == null || course.getName() == null ||
                course.getNumber().equals("") || course.getName().equals("")) {

            throw new InvalidInformationException("Course number/name cannot be null or empty");
        }
        return true;
    }

}
