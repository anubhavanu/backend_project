package com.example.fileupload.service;

import com.example.fileupload.dto.Country;
import com.example.fileupload.model.CountryImp;
import com.example.fileupload.repository.CountryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CountryService implements ICountryService{
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    FileSystemStorageService fileSystemStorageService;


    public Country[] fetchCountryData() throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl = "https://restcountries.com/v3.1/name/india?fullText=true";
        String fooResourceUrl = "https://restcountries.com/v3.1/name/in";

        String response  = String.valueOf(restTemplate.getForEntity(fooResourceUrl , String.class).getBody());

        ObjectMapper mapper = new ObjectMapper();
        Country [] countries = mapper.readValue(response, Country[].class);
        ArrayList<CountryImp> countryImpList = new ArrayList<CountryImp>();
        for (int i=0;i<countries.length;++i)
        {
            CountryImp countryImp=new CountryImp();
            countryImp.setCountry_name(countries[i].getName().getOfficial());
            countryImp.setFlag(countries[i].getFlags().getPng());

//            fileSystemStorageService.loadFile(countryImp.getFlag());


            String s="";
            if (countries[i].getCapitalInfo().getLatlng() !=null)
            {

                s= String.valueOf(countries[i].getCapitalInfo().getLatlng().get(0))+String.valueOf(countries[i].getCapitalInfo().getLatlng().get(1));
                countryImp.setGeolocation(s);
            }


            if (countries[i].getCapital() !=null)
            {
                String t="";

                for(int j=0;j<countries[i].getCapital().size();++j)
                {
                    t=t+countries[i].getCapital().get(j);
                }
                countryImp.setCapital(t);
            }
            countryImpList.add(countryImp);


        }
        countryRepository.saveAll(countryImpList);

        return countries;

    }

}
