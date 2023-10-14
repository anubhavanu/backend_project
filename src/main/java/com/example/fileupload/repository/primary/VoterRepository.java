package com.example.fileupload.repository.primary;

import com.example.fileupload.model.primary.VoterTableMongodb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends MongoRepository<VoterTableMongodb, Integer> {
}
