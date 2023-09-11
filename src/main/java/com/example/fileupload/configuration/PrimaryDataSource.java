//package com.example.fileupload.configuration;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class PrimaryDataSource {
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        DataSourceProperties dsp= new DataSourceProperties();
//        return dsp;
//    }
//
//    @Primary
//    @Bean("primarydb")
////    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource datasource(DataSourceProperties dataSourceProperties){
//        DataSource ds= dataSourceProperties.initializeDataSourceBuilder().build();
//        return ds;
//    }
//
//    }
//
//
//
//
