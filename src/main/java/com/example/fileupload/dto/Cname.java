package com.example.fileupload.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cname {

    String official;


}
