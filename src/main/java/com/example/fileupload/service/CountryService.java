package com.example.fileupload.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CountryService {
    public ResponseEntity<String> getForEntity()
    {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://restcountries.com/v3.1/name/india?fullText=true";
        ResponseEntity<String> response  = restTemplate.getForEntity(fooResourceUrl , String.class);
        return response;

    }


}
