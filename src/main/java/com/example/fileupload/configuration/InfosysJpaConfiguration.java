package com.example.fileupload.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.example.fileupload.repository.infosys"},
        entityManagerFactoryRef = "infosysEntityManagerFactory",
        transactionManagerRef = "infosysTransactionManager"
)
public class InfosysJpaConfiguration {

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


    @Bean("infosysproperties")
    @ConfigurationProperties("spring.datasource.infosys")
    public DataSourceProperties infosysproperties() {
        DataSourceProperties dsp= new DataSourceProperties();
        return dsp;
    }

    @Bean("infosysdb")

    public DataSource quartzdatasource( @Qualifier("infosysproperties") DataSourceProperties infosysproperties){
        DataSource ds= infosysproperties.initializeDataSourceBuilder().build();
        return ds;
    }

    @Bean("infosysEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean infosysEntityManagerFactory(
            @Qualifier("infosysdb") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean lcemfb=
                builder
                        .dataSource(dataSource)
                        .packages("com.example.fileupload.model.infosys")
                        .build();

        return lcemfb;
    }

    @Bean("infosysTransactionManager")

    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("infosysEntityManagerFactory") EntityManagerFactory infosysEntityManagerFactory) {
        return new JpaTransactionManager(infosysEntityManagerFactory);



    }



}