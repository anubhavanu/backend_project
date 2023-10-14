package com.example.fileupload.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDbConfig  {

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://ec2-13-233-123-95.ap-south-1.compute.amazonaws.com:27017/voterlist");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
//    @Bean
//    public mongoclient mongoclient() {
//        return new mongoclient("localhost", 27017);
//    }
//
//    @Bean
//    public mongotemplate mongotemplate(mongoclient mongoclient) throws exception {
//        return new mongotemplate(mongoclient, "databasename");
//    }


    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "voterlist");
    }
}
