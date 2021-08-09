package com.revature.registration.util;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.revature.registration.util.exceptions.DataSourceException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ConnectionFactory {

    private final MongoClient mongoClient;
    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    public ConnectionFactory() {
        Properties appProperties = new Properties();

        try {

            appProperties.load(new FileReader("src/main/resources/app.properties"));

            String ipAddress = appProperties.getProperty("ipAddress");
            int port = Integer.parseInt(appProperties.getProperty("port"));
            String username = appProperties.getProperty("username");
            String dbName = appProperties.getProperty("dbName");
            char[] password = appProperties.getProperty("password").toCharArray();

            List<ServerAddress> hosts = Collections.singletonList(new ServerAddress(ipAddress,port));
            MongoCredential credentials = MongoCredential.createScramSha1Credential(username,dbName,password);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyToClusterSettings(builder -> builder.hosts(hosts))
                    .credential(credentials).build();

            this.mongoClient = MongoClients.create(settings);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace(); // TODO log this
            throw new DataSourceException("Unable to load database properties file",fnfe);
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this
            throw new DataSourceException("An unexpected problem occurred", e);
        }
    }

    public void cleanUp() {
        mongoClient.close();
    }

    public static ConnectionFactory getInstance() {
        return connectionFactory;
    }

    public MongoClient getConnection() {
        return mongoClient;
    }
}
