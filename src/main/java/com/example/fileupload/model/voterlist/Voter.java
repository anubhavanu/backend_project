package com.example.fileupload.model.voterlist;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@Data
@Document(collection = "voter")
public class Voter {
    private int id;
    private String name;
    private int age;
    private String city;

}
