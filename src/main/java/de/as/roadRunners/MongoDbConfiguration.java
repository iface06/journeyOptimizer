package de.as.roadRunners;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EnableMongoRepositories
public class MongoDbConfiguration extends AbstractMongoConfiguration {

    private static final String HOST_NAME = "ds029911.mongolab.com";
    private static final int PORT = 29911;
    private static final String DB_NAME = "ttmasterthesisdb";
    private static final String USER = "roadrunners";
    private static final String PASSWORD = "S%634dTk!";

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(USER, PASSWORD);
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoClient client = new MongoClient(HOST_NAME, PORT);
        return client;
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    @Override
    protected String getMappingBasePackage() {
        return "de.as.roadRunners.persistence";
    }

}
