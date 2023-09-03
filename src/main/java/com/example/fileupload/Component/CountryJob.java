package com.example.fileupload.Component;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class CountryJob implements Job {
//    static Log logger = (Log) LogFactory.getLog(CountryService.class);

//    @Autowired
//    @Qualifier("countryservice1")
//    CountryService countryService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
//            CountryService countryService=new CountryService();
//          countryService.fetchCountryData();
//
            System.out.println("jhelloooo");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
