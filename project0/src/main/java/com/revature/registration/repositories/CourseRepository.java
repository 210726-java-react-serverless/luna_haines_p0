package com.revature.registration.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.registration.models.Course;
import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.util.ConnectionFactory;
import com.revature.registration.util.exceptions.DataSourceException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements CrudRepository<Course>{

    @Override
    public Course save(Course newCourse) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase courseDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> courseCollection = courseDb.getCollection("course");
        Document newCourseDoc = new Document("number",newCourse.getNumber())
                .append("name",newCourse.getName())
                .append("description",newCourse.getDescription())
                .append("professor",newCourse.getProfessor())
                .append("students",newCourse.getStudents());

        courseCollection.insertOne(newCourseDoc);
        newCourse.setId(newCourseDoc.get("_id").toString());

        return newCourse;
    }

    @Override
    public Course findById(String id) {
        try {
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase courseDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> courseCollection = courseDb.getCollection("course");
            Document query = new Document("_id", id);
            Document result = courseCollection.find(query).first();

            ObjectMapper mapper = new ObjectMapper();
            Course course = mapper.readValue(result.toJson(), Course.class);
            course.setId(result.get("_id").toString());

            return course;
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    // TODO add findByProf method

    public Course findByNumber(String number) {
        try {
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase courseDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> courseCollection = courseDb.getCollection("course");
            Document query = new Document("number",number);
            Document result = courseCollection.find(query).first();

            ObjectMapper mapper = new ObjectMapper();
            Course course = mapper.readValue(result.toJson(),Course.class);
            course.setId(result.get("_id").toString());

            return course;
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public List<Course> findByStudent(Student student) {
        try {
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase courseDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> courseCollection = courseDb.getCollection("course");
            Document query = new Document("email", student.getEmail());
            List<Document> result = courseCollection.find(query).into(new ArrayList<>());

            ObjectMapper mapper = new ObjectMapper();
            List<Course> registeredCourses = new ArrayList<>();

            for (Document d : result) {
                registeredCourses.add(mapper.readValue(d.toJson(), Course.class));
            }
            return registeredCourses;
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public List<Course> findByFaculty(Faculty faculty) {
        try {
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase courseDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> courseCollection = courseDb.getCollection("course");
            Document query = new Document("professor", faculty.getEmail());
            List<Document> result = courseCollection.find(query).into(new ArrayList<>());

            // TODO result has no items in it. why???
            ObjectMapper mapper = new ObjectMapper();
            List<Course> taughtCourses = new ArrayList<>();

            for (Document d : result) {
                Course c = mapper.readValue(d.toJson(),Course.class);
                c.setId(d.get("_id").toString());
                taughtCourses.add(c);
            }
            return taughtCourses;
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public List<Course> findAll() {
        try {
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase courseDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> courseCollection = courseDb.getCollection("course");
            List<Document> courseDocList = courseCollection.find().into(new ArrayList<>());
            List<Course> courseList = new ArrayList<>();

            ObjectMapper mapper = new ObjectMapper();

            for (Document d : courseDocList) {
                courseList.add(mapper.readValue(d.toJson(), Course.class));
            }
            return courseList;
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }


    @Override
    public boolean update(Course updatedCourse, String field, String newValue) {
        return false;
    }

    // TODO add addStudent method
    // TODO add removeStudent method

    @Override
    public boolean deleteById(String id) {
        // TODO delete from student and faculty collections
        return false;
    }
}
