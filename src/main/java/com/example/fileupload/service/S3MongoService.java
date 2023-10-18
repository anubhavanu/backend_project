package com.example.fileupload.service;

import com.example.fileupload.helper.MyException;
import com.example.fileupload.model.voterlist.ShareDetails;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3MongoService {
    @Value("${application.bucket.name}")
    private String bucketName;
    @Autowired
    S3Client s3Client;

    public List<ShareDetails> csvToShareDetails(String filename)
    {
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

}
