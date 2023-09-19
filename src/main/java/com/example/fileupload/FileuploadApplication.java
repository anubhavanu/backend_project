package com.example.fileupload;

import com.example.fileupload.Component.TestClass;
import com.example.fileupload.service.SchedulerService;
import com.example.fileupload.service.TestAsync;
import com.example.fileupload.service.TestRepo;
import org.aspectj.weaver.ast.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.*;


@SpringBootApplication
@EnableTransactionManagement
public class FileuploadApplication extends Exception{

	public static void main(String[] args) {
		try {
			ApplicationContext context  =SpringApplication.run(FileuploadApplication.class, args);
			TestRepo tr=context.getBean(TestRepo.class);
			TestClass trc=context.getBean(TestClass.class);
//			trc.Convert();
			trc.nachUploadLauncher();

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
