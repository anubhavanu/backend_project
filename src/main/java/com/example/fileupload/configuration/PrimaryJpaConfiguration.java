package com.example.fileupload.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.example.fileupload.repository"},
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryJpaConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties dsp= new DataSourceProperties();
        return dsp;
    }

    @Primary
    @Bean("primarydb")
//    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource(DataSourceProperties dataSourceProperties){
        DataSource ds= dataSourceProperties.initializeDataSourceBuilder().build();
        return ds;
    }

    @Bean("primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            @Qualifier("primarydb") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        Map<String,Object> properties=new HashMap<String,Object>();
        properties.put("hibernate.hbm2ddl.auto","create");

        LocalContainerEntityManagerFactoryBean lcemfb=
                                builder
                                .dataSource(dataSource)
                                .packages("com.example.fileupload.model")
                                .properties(properties)
                                .build();

        return lcemfb;
    }

    @Bean
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }

}