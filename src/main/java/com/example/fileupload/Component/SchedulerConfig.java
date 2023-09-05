//package Component;
//
//import org.apache.catalina.core.ApplicationContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Component
//public class SchedulerConfig {
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private QuartzProperties quartzProperties;
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() {
//
//        SchedulerJobFactory jobFactory = new SchedulerJobFactory();
//        jobFactory.setApplicationContext(applicationContext);
//
//        Properties properties = new Properties();
//        properties.putAll(quartzProperties.getProperties());
//
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setOverwriteExistingJobs(true);
//        factory.setDataSource(dataSource);
//        factory.setQuartzProperties(properties);
//        factory.setJobFactory(jobFactory);
//        return factory;
//    }
//}
