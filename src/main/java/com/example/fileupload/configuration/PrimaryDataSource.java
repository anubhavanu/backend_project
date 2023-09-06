package com.example.fileupload.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class PrimaryDataSource {
//    @Bean(name="datasource")
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource getPrimaryDataSource() {
//        DataSource ds= DataSourceBuilder.create().build();
//
//
////        DataSource ds= DataSourceBuilder.create().url("jdbc:postgresql://ec2-15-206-91-250.ap-south-1.compute.amazonaws.com:2000/postgres")
////        .username("user")
////        .password("password")
////        .driverClassName("org.postgresql.Driver").build();
//
//
//        return ds;
//
//    }



    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties dsp= new DataSourceProperties();
        return dsp;
    }

    @Primary
    @Bean(name="datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource(DataSourceProperties properties){
        DataSource ds= properties.initializeDataSourceBuilder().build();
        return ds;
    }


}
