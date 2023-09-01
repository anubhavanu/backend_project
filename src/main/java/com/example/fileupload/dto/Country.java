package com.example.fileupload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Country  {

    private Cname name;

    Currency currencies;

    private ArrayList<String> capital;



    Flag flags;


    CapitalInfo capitalInfo;

}
