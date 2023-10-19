package com.example.fileupload.controller;

import com.example.fileupload.service.S3MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulkMongoCSVUpload {
    @Autowired
    S3MongoService s3MongoService;

    @PostMapping("/uploadMongo")
    public ResponseEntity<?> addCSVData(@RequestBody String filename)
    {
        s3MongoService.addShareDetails(filename);
        return ResponseEntity.ok("Data uploaded successfully");
    }
}
