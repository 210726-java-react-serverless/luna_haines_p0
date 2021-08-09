package com.revature.registration.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.revature.registration.models.Course;
import com.revature.registration.util.ConnectionFactory;
import com.revature.registration.util.exceptions.DataSourceException;
import org.bson.Document;

public class CourseRepository implements CrudRepository<Course>{

    @Override
    public Course save(Course newCourse) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase courseDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> courseCollection = courseDb.getCollection("course");
        Document newCourseDoc = new Document("number",newCourse.getNumber())
                .append("name",newCourse.getName())
                .append("description",newCourse.getDescription())
                .append("professor",newCourse.getProfessorEmail())
                .append("students",newCourse.getStudentEmails());

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

    @Override
    public boolean update(Course updatedCourse, String field, String newValue) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
