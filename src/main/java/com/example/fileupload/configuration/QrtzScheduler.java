package com.example.fileupload.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class QrtzScheduler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;

//    @Autowired
//    QuartzProperties quartzProperties;
    @PostConstruct
    public void init() {
        logger.info("Hello world from Quartz...");
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        logger.debug("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    @Bean(name="quartzproperties")
    @ConfigurationProperties("spring.datasource.quartz")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties dsp= new DataSourceProperties();
        return dsp;
    }

    @Bean(name="quartzdatasource")
    @QuartzDataSource
//    @ConfigurationProperties(prefix = "spring.datasource.quartz")
    public DataSource datasource(@Qualifier("quartzproperties") DataSourceProperties properties){
        DataSource ds= properties.initializeDataSourceBuilder().build();
        return ds;
    }


}