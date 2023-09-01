package com.example.fileupload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data

@JsonIgnoreProperties(ignoreUnknown = true)
public class CapitalInfo {
    private ArrayList<Float> latlng;
}
