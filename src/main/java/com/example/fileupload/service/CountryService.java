package com.example.fileupload.service;

import com.example.fileupload.model.Country;
import com.example.fileupload.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CountryService implements ICountryService{
    @Autowired
    CountryRepository countryRepossitory;
    public ResponseEntity<String> getForEntity()
    {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://restcountries.com/v3.1/name/india?fullText=true";
        ResponseEntity<String> response  = restTemplate.getForEntity(fooResourceUrl , String.class);
        return response;

    }
    @Override
    public String addCountry(Country country)
    {
     Country c=countryRepossitory.findCountryByName(country);
     if (c==null)
     {
         countryRepossitory.save(country);
         return ("Country added successfully");
     }
     else
         return ("Country already present");
    }





}
