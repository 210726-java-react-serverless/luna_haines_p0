package com.revature.registration.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.revature.registration.models.Faculty;
import com.revature.registration.util.ConnectionFactory;
import com.revature.registration.util.exceptions.DataSourceException;
import com.revature.registration.util.exceptions.InvalidInformationException;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;

public class FacultyRepository implements CrudRepository<Faculty>{

    @Override
    public Faculty save(Faculty newFaculty) {

        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase facultyDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> facultyCollection = facultyDb.getCollection("faculty");
        Document newFacultyDoc = new Document("firstName", newFaculty.getFirstName())
                .append("lastName",newFaculty.getLastName())
                .append("email",newFaculty.getEmail())
                .append("password",newFaculty.getPassword());

        facultyCollection.insertOne(newFacultyDoc);
        newFaculty.setId(newFacultyDoc.get("_id").toString());

        return newFaculty;
    }

    @Override
    public Faculty findById(String id) {

        try {
            // TODO obfuscate dbName and collectionName with properties
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase facultyDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> facultyCollection = facultyDb.getCollection("faculty");
            Document queryDoc = new Document("_id", id);
            Document returnDoc = facultyCollection.find(queryDoc).first();

            ObjectMapper mapper = new ObjectMapper();
            Faculty faculty = mapper.readValue(returnDoc.toJson(), Faculty.class);
            faculty.setId(returnDoc.get("_id").toString());

            return faculty;
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    public Faculty findByCredentials(String email, String password) {

        try {
            // TODO obfuscate dbName and collectionName with properties
            MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
            MongoDatabase facultyDb = mongoClient.getDatabase("p0");
            MongoCollection<Document> facultyCollection = facultyDb.getCollection("faculty");
            Document queryDoc = new Document("email", email).append("password", password);
            Document authDoc = facultyCollection.find(queryDoc).first();

            if (authDoc == null) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            Faculty faculty = mapper.readValue(authDoc.toJson(), Faculty.class);
            faculty.setId(authDoc.get("_id").toString());

            return faculty;
        } catch (JsonMappingException jme) {
            jme.printStackTrace();
            throw new DataSourceException("An exception occurred while mapping the Document",jme);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("An unexpected exception occurred",e);
        }
    }

    @Override
    public boolean update(Faculty updateFaculty,String field,String newValue) {

        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase facultyDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> facultyCollection = facultyDb.getCollection("faculty");
        Document queryDoc = new Document(field,newValue);

        // TODO create if statements with other potential problems
        if (field.equals("email") && facultyCollection.find(queryDoc) != null) {
            return false;
        }

        facultyCollection.updateOne(Filters.eq("email",updateFaculty.getEmail()), Updates.set(field,newValue));

        return true;
    }

    @Override
    public boolean deleteById(String id) {

        MongoClient mongoClient = ConnectionFactory.getInstance().getConnection();
        MongoDatabase facultyDb = mongoClient.getDatabase("p0");
        MongoCollection<Document> facultyCollection =  facultyDb.getCollection("faculty");

        if (facultyCollection.find(Filters.eq("_id",id)) == null) {
            return false;
        }

        facultyCollection.deleteOne(Filters.eq("_id",id));
        return true;
    }
}
