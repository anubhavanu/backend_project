package com.example.fileupload.repository.voterlist;

import com.example.fileupload.model.voterlist.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends MongoRepository<Voter, Integer> {
}
