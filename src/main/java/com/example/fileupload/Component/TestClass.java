package com.example.fileupload.Component;

import com.example.fileupload.dto.Employee;
import com.example.fileupload.dto.JsonSchema;
import com.example.fileupload.service.TestAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@Component
public class TestClass {

    static Logger logger = LoggerFactory.getLogger(TestClass.class);
    @Autowired
    Job nachProcessJob;

    @Autowired
    JobLauncher jbLauncher;
    public  void Convert() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {


//        Employee emp;
        String className="com.example.fileupload.dto.Employee";

        ArrayList< JsonSchema> headers = new ArrayList<JsonSchema>();
        headers.add(new JsonSchema("name","string"));
        headers.add(new JsonSchema("age","int"));
        headers.add(new JsonSchema("salary","float"));

        Class<?> anyClass = Class.forName(className);
        Constructor<?> ctor = anyClass.getConstructor();
        Object object = ctor.newInstance();


        for(JsonSchema js: headers){

            Field field = anyClass.getDeclaredField(js.getFieldName());
            field.setAccessible(true);
            switch (js.getFieldType())
            {
                case "string":
                    field.set(object,"hello");
                    break;
                case "int":
                    field.set(object,123);
                    break;
                case "float":
                    field.set(object, 10.10F);
                    break;
            }

        }

        logger.info("printing****");

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
//        jbLauncher.run(dboProcessJob, jobParameters);

    }

    public void nachUploadLauncher() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jbLauncher.run(nachProcessJob, jobParameters);

    }
}
