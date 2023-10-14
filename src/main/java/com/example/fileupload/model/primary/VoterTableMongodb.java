package com.example.fileupload.model.primary;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@Data
@Document(collection = "voter")
public class VoterTableMongodb {
    private int id;
    private String name;
    private int age;
    private int city;

}
