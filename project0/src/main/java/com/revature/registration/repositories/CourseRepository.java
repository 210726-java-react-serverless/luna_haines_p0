package com.revature.registration.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.revature.registration.models.Course;
import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.util.ConnectionFactory;
import com.revature.registration.util.exceptions.DataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository{

    private final Logger logger = LogManager.getLogger(CourseRepository.class);

    public Course save(Course newCourse) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase courseDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> courseCollection = courseDb.getCollection("course");
        Document newCourseDoc = new Document("number",newCourse.getNumber())
                .append("name",newCourse.getName())
                .append("description",newCourse.getDescription())
                .append("professor",newCourse.getProfessor())
                .append("capacity",newCourse.getCapacity())
                .append("students",newCourse.getStudents());

        courseCollection.insertOne(newCourseDoc);
        newCourse.setId(newCourseDoc.get("_id").toString());

        return newCourse;
    }

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
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

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
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public List<Course> findByStudent(Student student) {
        try {
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase courseDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> courseCollection = courseDb.getCollection("course");
            List<Document> result = courseCollection.find(Filters.in("students",student.getEmail())).into(new ArrayList<>());

            ObjectMapper mapper = new ObjectMapper();
            List<Course> registeredCourses = new ArrayList<>();

            for (Document d : result) {
                registeredCourses.add(mapper.readValue(d.toJson(), Course.class));
            }
            return registeredCourses;
        } catch (JsonMappingException jme) {
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
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

            ObjectMapper mapper = new ObjectMapper();
            List<Course> taughtCourses = new ArrayList<>();

            for (Document d : result) {
                Course c = mapper.readValue(d.toJson(),Course.class);
                c.setId(d.get("_id").toString());
                taughtCourses.add(c);
            }
            return taughtCourses;
        } catch (JsonMappingException jme) {
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
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
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public boolean update(String currentNumber, String field, String newValue) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase courseDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> courseCollection = courseDb.getCollection("course");
        Document updateCourseDoc = new Document(field,newValue);

        if (field.equals("number") && courseCollection.find(Filters.eq("number",newValue)) != null) {
            return false;
        }

        if (courseCollection.find(Filters.eq("number",currentNumber)) == null) {
            return false;
        }

        courseCollection.updateOne(Filters.eq("number",currentNumber),
                Updates.set(field,newValue));
        return true;
    }


    public boolean addStudent(String courseNumber, String email) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase courseDb = mongoClient.getDatabase("p0");
        MongoCollection courseCollection = courseDb.getCollection("course");

        courseCollection.updateOne(Filters.eq("number",courseNumber),
                Updates.addToSet("students",email));
        return true;
    }

    public boolean removeStudent(String courseNumber, String email) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase courseDb = mongoClient.getDatabase("p0");
        MongoCollection courseCollection = courseDb.getCollection("course");

        courseCollection.updateOne(Filters.eq("number",courseNumber),Updates.pull("students",email));
        return true;
    }

    public boolean deleteByNumber(String number) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase courseDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> courseCollection = courseDb.getCollection("course");

        if (courseCollection.find(Filters.eq("number",number)) == null) {
            return false;
        }
        courseCollection.deleteOne(Filters.eq("number",number));
        return true;
    }
}
