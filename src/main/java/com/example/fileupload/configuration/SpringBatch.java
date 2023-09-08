package com.example.fileupload.configuration;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SpringBatch {

    @Bean("jbRepository")
    @DependsOn("quartzdatasource")
    public JobRepository jbRepository( @Qualifier("quartzdatasource") DataSource quartzdatasource,
                                       PlatformTransactionManager transactionManager
                                       ) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.POSTGRES.name());
        jobRepositoryFactoryBean.setDataSource(quartzdatasource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_DEFAULT");
        jobRepositoryFactoryBean.setTablePrefix("BATCH_");
//        jobRepositoryFactoryBean.setIncrementerFactory(new DefaultDataFieldMaxValueIncrementerFactory(dataSource) {
//            @Override
//            public DataFieldMaxValueIncrementer getIncrementer(String incrementerType, String incrementerName) {
//                return new SqlServerSequenceMaxValueIncrementer(dataSource, incrementerName);
//            }
//        });
        jobRepositoryFactoryBean.afterPropertiesSet();
        JobRepository jbr= jobRepositoryFactoryBean.getObject();
         return jbr;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }
    @Bean
    public JobLauncher jbLauncher( JobRepository jbRepository) throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jbRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
}
