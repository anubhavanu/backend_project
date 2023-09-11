package com.example.fileupload.service;

import com.example.fileupload.dto.Country;
import com.example.fileupload.model.CountryImp;
import com.example.fileupload.model.CountryTable;
import com.example.fileupload.repository.CountryRepository;
import com.example.fileupload.repository.CountryTableRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CountryService  implements ICountryService{
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryTableRepository countryTableRepository;

    @Autowired
    FileSystemStorageService fileSystemStorageService;

//
//    private final SchedulerService schedulerService;
//    @Autowired
//    public CountryService(SchedulerService schedulerService) {
//        this.schedulerService = schedulerService;
//    }
//    public void run() throws JsonProcessingException {
//        final TimerInfo timerInfo=new TimerInfo();
//        timerInfo.setTotalFireCount(1);
//        timerInfo.setCallBackData(fetchCountryData());
//        timerInfo.setInitialOffsetMs(1000);
//        timerInfo.setRepeatedInterval(300000);
////        Scheduler.schedule(CountryService.class,timerInfo);
//    }

     public Country[] fetchCountryData() throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl = "https://restcountries.com/v3.1/name/india?fullText=true";
        String fooResourceUrl = "https://restcountries.com/v3.1/name/u";

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
        System.out.println("Runnibg the code");
        countryRepository.saveAll(countryImpList);

        return countries;

    }
    public Country[] fetchCountryTableData() throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl = "https://restcountries.com/v3.1/name/india?fullText=true";
        String fooResourceUrl = "https://restcountries.com/v3.1/name/f";

        String response  = String.valueOf(restTemplate.getForEntity(fooResourceUrl , String.class).getBody());

        ObjectMapper mapper = new ObjectMapper();
        Country [] countries = mapper.readValue(response, Country[].class);
        ArrayList<CountryTable> countryImpList = new ArrayList<CountryTable>();
        for (int i=0;i<countries.length;++i)
        {
            CountryTable countryImp=new CountryTable();
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
        System.out.println("Runnibg the code");
        //        countryTableRepository.saveAll(countryImpList);
        countryTableRepository.save(countryImpList.get(0));


        return countries;

    }




}
