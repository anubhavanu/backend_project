package com.example.fileupload.repository.voterlist;

import com.example.fileupload.model.voterlist.ShareDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends MongoRepository<ShareDetails,Integer> {
}
