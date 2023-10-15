package com.example.fileupload.repository.primary;

import com.example.fileupload.model.primary.VoterTableMongodb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.example.fileupload.repository")

public interface VoterRepository extends MongoRepository<VoterTableMongodb, Integer> {
}
