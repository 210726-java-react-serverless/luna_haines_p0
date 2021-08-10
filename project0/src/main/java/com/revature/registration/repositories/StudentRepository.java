package com.revature.registration.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.revature.registration.models.Faculty;
import com.revature.registration.models.Student;
import com.revature.registration.util.ConnectionFactory;
import com.revature.registration.util.exceptions.DataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

public class StudentRepository implements CrudRepository<Student>{

    private final Logger logger = LogManager.getLogger(StudentRepository.class);

    @Override
    public Student save(Student newStudent) {
        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase studentDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> studentCollection = studentDb.getCollection("student");
        Document newStudentDoc = new Document("firstName", newStudent.getFirstName())
                .append("lastName",newStudent.getLastName())
                .append("email",newStudent.getEmail())
                .append("password",newStudent.getPassword());

        studentCollection.insertOne(newStudentDoc);
        newStudent.setId(newStudentDoc.get("_id").toString());

        return newStudent;
    }

    @Override
    public Student findById(String id) {
        try {
            // TODO obfuscate dbName and collectionName with properties
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase studentDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> studentCollection = studentDb.getCollection("student");
            Document queryDoc = new Document("_id", id);
            Document returnDoc = studentCollection.find(queryDoc).first();

            ObjectMapper mapper = new ObjectMapper();
            Student student = mapper.readValue(returnDoc.toJson(), Student.class);
            student.setId(returnDoc.get("_id").toString());

            return student;
        } catch (JsonMappingException jme) {
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public Student findByEmail(String email) {
        try {
            // TODO obfuscate dbName and collectionName with properties
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase studentDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> studentCollection = studentDb.getCollection("student");
            Document queryDoc = new Document("email", email);
            Document returnDoc = studentCollection.find(queryDoc).first();

            ObjectMapper mapper = new ObjectMapper();
            Student student = mapper.readValue(returnDoc.toJson(), Student.class);
            student.setId(returnDoc.get("_id").toString());

            return student;
        } catch (JsonMappingException jme) {
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public Student findByCredentials(String email,String password) {
        try {
            // TODO obfuscate dbName and collectionName with properties
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase studentDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> studentCollection = studentDb.getCollection("student");
            Document queryDoc = new Document("email", email).append("password", password);
            Document authDoc = studentCollection.find(queryDoc).first();

            if (authDoc == null) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            Student student = mapper.readValue(authDoc.toJson(), Student.class);
            student.setId(authDoc.get("_id").toString());

            return student;
        } catch (JsonMappingException jme) {
            logger.debug(jme.getMessage());
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    @Override
    public boolean update(Student updateStudent,String field,String newValue) {

        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase studentDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> studentCollection = studentDb.getCollection("student");
        Document queryDoc = new Document(field,newValue);

        // TODO create if statements with other potential problems
        if (field.equals("email") && studentCollection.find(queryDoc) != null) {
            return false;
        }

        studentCollection.updateOne(Filters.eq("email",updateStudent.getEmail()), Updates.set(field,newValue));

        return true;
    }

    @Override
    public boolean deleteById(String id) {
        // TODO remove student from courses

        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase studentDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> studentCollection =  studentDb.getCollection("student");

        if (studentCollection.find(Filters.eq("_id",id)) == null) {
            return false;
        }

        studentCollection.deleteOne(Filters.eq("_id",id));
        return true;
    }
}
