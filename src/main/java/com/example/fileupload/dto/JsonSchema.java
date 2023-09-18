package com.example.fileupload.dto;

import lombok.Data;

@Data
public class JsonSchema {

    String fieldName;
    String fieldType;

    public JsonSchema(String fieldName,String fieldType){
        this.fieldName=fieldName;
        this.fieldType=fieldType;
    }
}
