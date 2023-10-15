package com.example.fileupload;

import com.example.fileupload.configuration.MongoDbConfig;
import com.example.fileupload.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
//@EnableTransactionManagement
public class FileuploadApplication extends Exception{

	public static void main(String[] args) {
		try {
			ApplicationContext context  =SpringApplication.run(FileuploadApplication.class, args);
			TestRepo tr=context.getBean(TestRepo.class);
			EmployeeService es=context.getBean(EmployeeService.class);
//			es.cacheEmployee();

			VoterTest vt=context.getBean(VoterTest.class);
			vt.addVoter();

//			ExecutorService executor = Executors.newFixedThreadPool(5);
//			executor.submit(() -> {
//				try {
//					tr.startTxnQueuelistner();
//				} catch (JsonProcessingException e) {
//					throw new RuntimeException(e);
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//			});

			TestAsync ta = context.getBean(TestAsync.class);
			ta.saveMultipleData1();
//			ExecutorService executor = Executors.newFixedThreadPool(10);
//			Callable<String> callableTask = () -> {
//				TimeUnit.MILLISECONDS.sleep(300);
//				ta.sample1();
//				return "Task's execution";
//			};
//			Future<String> future = executor.submit(callableTask);


			SchedulerService schedulerService = context.getBean(SchedulerService.class);
			schedulerService.StartSchedulerService();
			MongoDbConfig mc=context.getBean(MongoDbConfig.class);
//			MongoTemplate mongo = context.getBean(MongoTemplate.class);
//			schedulerService.schedule();

//			MessageSender messageSender = context.getBean(MessageSender.class);
//			//messageSender.sendMessage();
//			MessageReciever messageReciever=context.getBean(MessageReciever.class);
//			String s=messageReciever.recieveMessage();




//
//			System.out.println(s);



			System.out.println("**********HEHHEHEHEHEHEHEHEH*********");
		}catch(Exception e)
		{System.out.println(e.getMessage());




	}


		}

}
