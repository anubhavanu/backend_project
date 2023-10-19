package com.example.fileupload.service;

import com.example.fileupload.model.voterlist.ShareDetails;
import com.example.fileupload.repository.voterlist.ShareRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3MongoService {
    @Value("${application.bucket.name}")
    private String bucketName;
    @Autowired
    @Qualifier("s3c")
    S3Client s3Client;

    @Autowired
    ShareRepository shareRepository;

    Logger logger=  LoggerFactory.getLogger(S3MongoService.class);

    public List<ShareDetails> csvToShareDetails(String filename)
    {

        listObjects(bucketName);
        ListBucketsResponse listBucketsResponse = s3Client.listBuckets();


        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        //filename="sample_chunk_record_sheet";
        ResponseBytes<GetObjectResponse> responseResponseBytes = s3Client.getObjectAsBytes(objectRequest);
        byte[] data = responseResponseBytes.asByteArray();
        InputStream is = new ByteArrayInputStream(data);
       try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){
           List<ShareDetails> sd =new ArrayList<>();
           Iterable<CSVRecord> csvRecords = csvParser.getRecords();
           for (CSVRecord csvRecord : csvRecords){
               ShareDetails s=new ShareDetails(csvRecord.get("shareName"),
                       csvRecord.get("shareHolder"),
                       Integer.parseInt(csvRecord.get("quantity")),
                       Float.parseFloat(csvRecord.get("shareCost")),
                       Float.parseFloat(csvRecord.get("profit")));
               sd.add(s);
           }
           return sd;
       }catch (IOException e) {
           throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
       }


    }
    public void addShareDetails(String filename)
    {
        shareRepository.saveAll(csvToShareDetails(filename));
    }

    public void listObjects(String bucketName) {
        ListObjectsV2Iterable listObjectsV2Iterable =  s3Client.listObjectsV2Paginator(builder -> builder.bucket(bucketName));
        logger.info("Objects in {} bucket: ", bucketName);
        listObjectsV2Iterable.contents().stream()
                .forEach(content -> logger.debug("{} {} bytes", content.key(), content.size()));
    }

}

//AWS_ACCESS_KEY_ID=AKIAVUQ57EQQQYEN3FV4;AWS_SECRET_ACCESS_KEY=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