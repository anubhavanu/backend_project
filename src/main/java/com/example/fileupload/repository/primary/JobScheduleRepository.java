//package com.example.fileupload.repository;
//
//import com.example.fileupload.service.JobScheduler;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface JobScheduleRepository extends CrudRepository<JobScheduler,Long> {
//    @Query(value ="select q.job_name  from qrtz_job_details q WHERE q.job_name = ?1 limit 1 ",nativeQuery = true)
//    String findJobByName(String jobName);
//
//}
