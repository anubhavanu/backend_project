package com.example.fileupload.repository.cache;

import com.example.fileupload.model.infosys.Office;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

public class OfficeCacheRepo {
    final Logger logger = LoggerFactory.getLogger(OfficeCacheRepo.class);
    private HashOperations hashOperations;



    public OfficeCacheRepo(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }
    public void create(Office Office) {
        hashOperations.put("Office", Office.getOffice_id(), Office);
        logger.info(String.format("Office with ID %s saved", Office.getOffice_id()));
    }
    public Office get(int Office_Id) {
        return (Office) hashOperations.get("Office", Office_Id);
    }
    public Map<String, Office> getAll(){
        return hashOperations.entries("Office");
    }
    public void update(Office Office) {
        hashOperations.put("Office", Office.getOffice_id(), Office);
        logger.info(String.format("Employee with ID %s updated", Office.getOffice_id()));
    }
    public void delete(int Office_Id) {
        hashOperations.delete("Office", Office_Id);
        logger.info(String.format("Employee with ID %s deleted", Office_Id));
    }
}
