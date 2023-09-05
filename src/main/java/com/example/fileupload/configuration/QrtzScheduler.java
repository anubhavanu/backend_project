package com.example.fileupload.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.annotation.PostConstruct;

@Configuration
public class QrtzScheduler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;

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



//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(DataSource quartzDataSource) throws IOException {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setJobFactory(springBeanJobFactory());
////        factory.setQuartzProperties(quartzProperties());
////        factory.setConfigLocation(new ClassPathResource("quartz.properties"));
////        factory.setDataSource(quartzDataSource);
//        return factory;
//    }

//    @Bean
//    @QuartzDataSource
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource quartzDataSource() {
////        DataSource ds= DataSourceBuilder.create().build();
////        return ds;
//
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:postgresql://ec2-15-206-91-250.ap-south-1.compute.amazonaws.com:2000/quartz");
//        dataSourceBuilder.username("user");
//        dataSourceBuilder.password("password");
//        dataSourceBuilder.driverClassName("org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
//        return dataSourceBuilder.build();
//    }

//    @Bean
//    @QuartzDataSource
//    public DataSource quartzDataSource() {
//        return DataSourceBuilder.create().build();
//    }

}