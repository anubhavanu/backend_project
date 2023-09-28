package com.example.fileupload.repository.cache;

//import com.example.fileupload.dto.Employee;

import com.example.fileupload.model.infosys.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EmployeeRepo {

    final Logger logger = LoggerFactory.getLogger(EmployeeRepo.class);
    private HashOperations hashOperations;

//    @Autowired
//    RedisTemplate redisTemplate;

    public EmployeeRepo(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void create(Employee Employee) {
        hashOperations.put("Employee", Employee.getEmployee_id(), Employee);
        logger.info(String.format("Employee with ID %s saved", Employee.getEmployee_id()));
    }

    public Employee get(int EmployeeId) {
        return (Employee) hashOperations.get("Employee", EmployeeId);
    }

    public Map<String, Employee> getAll(){
        return hashOperations.entries("Employee");
    }

    public void update(Employee Employee) {
        hashOperations.put("Employee", Employee.getEmployee_id(), Employee);
        logger.info(String.format("Employee with ID %s updated", Employee.getEmployee_id()));
    }

    public void delete(int EmployeeId) {
        hashOperations.delete("Employee", EmployeeId);
        logger.info(String.format("Employee with ID %s deleted", EmployeeId));
    }
}