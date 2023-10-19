package com.example.fileupload.configuration;

//import com.example.fileupload.dto.Employee;

import com.example.fileupload.model.infosys.Employee;
import com.example.fileupload.model.infosys.Office;
import com.example.fileupload.repository.cache.EmployeeRepo;
import com.example.fileupload.repository.cache.OfficeCacheRepo;
import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;

@Configuration
public class RedisConfig  {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
        lcf.setHostName("ec2-13-126-138-154.ap-south-1.compute.amazonaws.com");
        lcf.setPort(2001);
        lcf.afterPropertiesSet();
        return lcf;
    }

//    @Bean
//    public LettuceBasedProxyManager set_proxy_mgr() {
//        io.lettuce.core.RedisClient redisClient = RedisClient.create("redis://user:password/ec2-13-233-123-95.ap-south-1.compute.amazonaws.com/6379");
//        LettuceBasedProxyManager proxyManager = LettuceBasedProxyManager.builderFor(redisClient).build();
//        return proxyManager;
//    }

//    @Bean
//     public        BucketConfiguration bconfig(int refillCount,int minutescount,int bucketSize)
//    {
//        BucketConfiguration configuration = BucketConfiguration.builder()
//                .addLimit(Bandwidth.classic(bucketSize, Refill.of(refillCount, Duration.ofMinutes(minutescount))))
//                .build();
//
//        return configuration;
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

    private Map<String, Bucket> user_bucket_map;
//    @Bean
//    public Map<String, Bucket> user_bucket_mapping()
//    {
//        return user_bucket_map;
//    }


}

