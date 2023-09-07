package com.example.fileupload.Component;

import com.example.fileupload.service.CountryService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryJob implements Job {
//    static Log logger = (Log) LogFactory.getLog(CountryService.class);

    @Autowired
    CountryService countryService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
//            CountryService countryService=new CountryService();
             countryService.fetchCountryData();
//
            System.out.println("jhelloooo");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
