package com.example.fileupload.configuration;

//import com.example.fileupload.dto.Employee;

import com.example.fileupload.model.infosys.Employee;
import com.example.fileupload.model.infosys.Office;
import com.example.fileupload.repository.cache.EmployeeRepo;
import com.example.fileupload.repository.cache.OfficeCacheRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig  {

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
//        lcf.setHostName("ec2-15-206-91-250.ap-south-1.compute.amazonaws.com");
//        lcf.setPort(2001);
//        lcf.afterPropertiesSet();
//        return lcf;
//    }

@Bean
    public RedisTemplate<String, Employee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Employee> redisTemplate = new RedisTemplate<String ,Employee>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//    redisTemplate.setDefaultSerializer(new JdkSerializationRedisSerializer());
    redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
    return redisTemplate;
    }
    @Bean
    public RedisTemplate<String, Office> redisTemplate1(RedisConnectionFactory redisConnectionFactory1) {
        RedisTemplate<String, Office> redisTemplate = new RedisTemplate<String ,Office>();
        redisTemplate.setConnectionFactory(redisConnectionFactory1);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    @Bean
    public OfficeCacheRepo officeCacheRepoository(RedisTemplate redisTemplate)
    {
        OfficeCacheRepo oc=new OfficeCacheRepo(redisTemplate);
        return oc;
    }

    @Bean
    public EmployeeRepo employeeRepository(RedisTemplate redisTemplate ){
        EmployeeRepo er = new EmployeeRepo(redisTemplate);
        return er;
    }

}

